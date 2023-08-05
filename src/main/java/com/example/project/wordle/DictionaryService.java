package com.example.project.wordle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DictionaryService {
    public synchronized static boolean checkValidity(String word) {
        try {
            String apiUrl = System.getenv("DICTIONARY_API") + word;

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return true;
            } else {
                System.out.println("ERROR CODE:" + connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return false;
    }

    public synchronized static String generateWord(int length) {
        try {
            String apiUrl = System.getenv("WRL_RANDOM_WORD_API") + length;

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
            e.printStackTrace(System.out);
        }
        return null;
    }
}
