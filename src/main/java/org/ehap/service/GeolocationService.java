package org.ehap.service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GeolocationService {
    private static final String API_URL = "https://geocoding-api.open-meteo.com/v1/search";

    public static void main(String[] args) {
        try {
            String query = "Berlin";

            JSONObject json = geocode(query);
            System.out.println(json.toString(2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject geocode(String query) throws IOException {
        // Codificar o parâmetro de consulta para ser usado na URL
        String encodedQuery = java.net.URLEncoder.encode(query, StandardCharsets.UTF_8);

        URL url = new URL(API_URL + "?name=" + encodedQuery + "&count=1");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

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

