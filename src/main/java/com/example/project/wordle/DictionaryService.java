package com.example.project.wordle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryService {

    public static boolean checkIfWordExists(String word) {
        try {
            // Construct the API request URL with the user input (should we store in .env ?)
            String apiUrl = "https://api.datamuse.com/words?sp=";
            String requestUrl = apiUrl + word;

            // Make the API request
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the API response

            BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println(responseReader);
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = responseReader.readLine()) != null) {
                response.append(line);
            }
            responseReader.close();

            /*
            Check if the word exists
            Datamuse returns either an epty list if it does not exist, or similar words with different
            scores based on the similarity. In our strict case if the first word does not match, then it does not exist
            */

            // Check if the response is not empty
            boolean responseIsNotEmpty = response.length() > 2;

            if (responseIsNotEmpty) {
                // Define the regular expression pattern to extract the first word
                Pattern pattern = Pattern.compile("\\{\"word\":\"(\\w+)\"");

                Matcher matcher = pattern.matcher(response);
                if (matcher.find()) {
                    if (matcher.group(1).equals(word)) {
                        return true;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
