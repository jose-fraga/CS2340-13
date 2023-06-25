package com.example.project.wordle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DictionaryService {
    public static void main(String[] args) {
        checkIfWordExists("hello");
    }

    public static boolean checkIfWordExists(String word) {
        try {
            // Construct the API request URL with the user input (should we store in .env ?)
            String apiUrl = "https://api.api-ninjas.com/v1/dictionary?word=" + word;
            String apiKey = "gVzGY7/ogCb0p9o7VO7dTw==Y5CfHFPPCRVQnBTA";

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestProperty("X-Api-Key", apiKey);

            //Add some checking here
            int responseCode = connection.getResponseCode();

            System.out.println(connection.getInputStream().toString());

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line = reader.readLine();
            while(line != null) {
                response.append(line);
                line = reader.readLine();
            }

            int index = response.toString().indexOf("valid") + 8;
            boolean isValid = Boolean.parseBoolean(response.substring(index).replace("}", ""));

            if (isValid) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("INVALID");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
