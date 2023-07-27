package com.example.project.wordle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DictionaryService {
    public synchronized static boolean checkValidity(String word) {
        try {
            String apiUrl = System.getenv("NINJA_API_URL") + word;
            String apiKey = System.getenv("NINJA_API_KEY");

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("X-Api-Key", apiKey);

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();

                String line = reader.readLine();
                while (line != null) {
                    response.append(line);
                    line = reader.readLine();
                }

                int index = response.toString().indexOf("valid") + 8;
                return Boolean.parseBoolean(response.substring(index).replace("}", ""));
            } else {
                System.out.println("ERROR CODE:" + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized static String generateWord(int length) {
        try {
            String apiUrl = System.getenv("RANDOM_WORD_API_URL") + length;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                StringBuilder response = new StringBuilder();

                String line = reader.readLine();
                while (line != null) {
                    response.append(line);
                    line = reader.readLine();
                }

                return response.toString().replace("[", "")
                        .replace("]", "")
                        .replace("\"", "");
            } else {
                System.out.println("ERROR CODE:" + connection.getResponseCode());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
