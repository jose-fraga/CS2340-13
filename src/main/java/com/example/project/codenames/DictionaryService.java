package com.example.project.codenames;

import com.example.project.codenames.enums.Type;
import org.json.JSONArray;

import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DictionaryService {
    private static ArrayList<Word> gameWords = new ArrayList<>();

    public static ArrayList<Word> getGameWords() { return gameWords; }

    public synchronized static void populate() {
//        for (int i = 0; i < 25; i++) {
//            String word = generateWord();
//            gameWords.add(new Word(word,false,obtainDefinition(word)));
//        }
        gameWords.clear();
        gameWords.addAll(List.of(
                new Word(Type.NEUTRAL,"Perception","The organisation, identification and interpretation of sensory information."),
                new Word(Type.NEUTRAL,"Scene","The location of an event that attracts attention."),
                new Word(Type.NEUTRAL,"Context","The surroundings, circumstances, environment, background or settings that determine, specify, or clarify the meaning of an event or other occurrence."),
                new Word(Type.NEUTRAL,"Improvement","The act of improving; advancement or growth; a bettering"),
                new Word(Type.NEUTRAL,"Cousin","The child of a person's uncle or aunt; a first cousin."),
                new Word(Type.NEUTRAL,"Election","A process of choosing a leader, members of parliament, councillors or other representatives by popular vote."),
                new Word(Type.NEUTRAL,"Professor","The most senior rank for an academic at a university or similar institution, informally also known as \"full professor.\" Abbreviated Prof."),
                new Word(Type.NEUTRAL,"Department","A part, portion, or subdivision."),
                new Word(Type.NEUTRAL,"Quality","Level of excellence."),
                new Word(Type.NEUTRAL,"Chocolate","A food made from ground roasted cocoa beans."),
                new Word(Type.NEUTRAL,"Woman","An adult female human."),
                new Word(Type.NEUTRAL,"Family","A group of people who are closely related to one another (by blood, marriage or adoption); kin; for example, a set of parents and their children; an immediate family."),
                new Word(Type.NEUTRAL,"World","Human collective existence; existence in general."),
                new Word(Type.NEUTRAL,"Finding","To encounter or discover by accident; to happen upon."),
                new Word(Type.NEUTRAL,"Poet","A person who writes poems."),
                new Word(Type.NEUTRAL,"Bread","A foodstuff made by baking dough made from cereals."),
                new Word(Type.NEUTRAL,"Disease","An abnormal condition of a human, animal or plant that causes discomfort or dysfunction; distinct from injury insofar as the latter is usually instantaneously acquired."),
                new Word(Type.NEUTRAL,"Queen","A female monarch. Example: Queen Victoria."),
                new Word(Type.NEUTRAL,"Opportunity","A chance for advancement, progress or profit."),
                new Word(Type.NEUTRAL,"Definition","A statement of the meaning of a word or word group or a sign or symbol (dictionary definitions)."),
                new Word(Type.NEUTRAL,"Description","A sketch or account of anything in words; a portraiture or representation in language; an enumeration of the essential qualities of a thing or species."),
                new Word(Type.NEUTRAL,"Examination","The act of examining."),
                new Word(Type.NEUTRAL,"Basis","A physical base or foundation."),
                new Word(Type.NEUTRAL,"Pizza","A baked Italian dish of a thinly rolled bread dough crust typically topped before baking with tomato sauce, cheese and other ingredients such as meat, vegetables or fruit"),
                new Word(Type.NEUTRAL,"Chest","A box, now usually a large strong box with a secure convex lid.")));
    }

    // Source: https://www.youtube.com/watch?v=9oq7Y8n1t00
    // Basically, I followed this tutorial to make GET request to the external API,
    // and how to read/handle the JSON resposne from such requests.
    public synchronized static String generateWord() {
        try {
            String CN_RANDOM_WORD_API = System.getenv("CN_RANDOM_WORD_API");

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URL(CN_RANDOM_WORD_API).toURI())
                    .GET()
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            return (String) new JSONArray(getResponse.body()).getJSONObject(0).get("word");
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    // Source: https://www.youtube.com/watch?v=9oq7Y8n1t00
    // Basically, I followed this tutorial to make GET request to the external API,
    // and how to read/handle the JSON resposne from such requests.
    public synchronized static String obtainDefinition(String word) {
        try {
            String DEFINITION_API = System.getenv("DICTIONARY_API");

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URL(DEFINITION_API + word).toURI())
                    .GET()
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            return (String) new JSONArray(getResponse.body())
                    .getJSONObject(0).getJSONArray("meanings").getJSONObject(0)
                    .getJSONArray("definitions").getJSONObject(0)
                    .get("definition");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
