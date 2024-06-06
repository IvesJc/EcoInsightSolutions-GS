package org.ehap.service;

import org.ehap.Main;
import org.ehap.exceptions.IncorrectRequestMethodException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Objects;

public class ViaCepService {

    private static final String API_URL = "https://viacep.com.br/ws/";


    public static JSONObject getCEP(String cep) throws IOException {

        URL url = new URL(API_URL + cep + "/json/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (!Objects.equals(conn.getRequestMethod(), "GET")) {
            throw new IncorrectRequestMethodException();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            result.append(line);
        }
        reader.close();

        return new JSONObject(result.toString());

    }
}
