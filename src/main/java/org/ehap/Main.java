package org.ehap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.ehap.infrastructure.CORSFilter;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

import static org.ehap.service.GeolocationService.geocode;
import static org.ehap.service.ViaCepService.getCEP;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8085/";

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in org.venda package
        final ResourceConfig rc = new ResourceConfig().packages("org.ehap");
        rc.register(new CORSFilter());
        rc.register(CORSFilter.class);
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {
        try {
            String query = "Berlin";
            JSONObject jsonGeoloc = geocode(query);
            System.out.println(jsonGeoloc.toString(2));

            String cep = "01508020";
            JSONObject jsonViaCep = getCEP(cep);
            System.out.println(jsonViaCep.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...", BASE_URI));
        System.in.read();
        server.stop();

    }
}

