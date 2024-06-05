package org.ehap.infrastructure;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.MultivaluedMap;

public class CORSFilter implements ContainerResponseFilter {

    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        // This is bad, only using this to allow connection between client and server
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Origin");
    }
}
