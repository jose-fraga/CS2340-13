package com.example.project.codenames;

import com.example.project.codenames.enums.Team;
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
        gameWords.addAll(List.of(
                new Word("Perception", Team.NEUTRAL,"The organisation, identification and interpretation of sensory information."),
                new Word("Scene",Team.NEUTRAL,"The location of an event that attracts attention."),
                new Word("Context",Team.NEUTRAL,"The surroundings, circumstances, environment, background or settings that determine, specify, or clarify the meaning of an event or other occurrence."),
                new Word("Improvement",Team.NEUTRAL,"The act of improving; advancement or growth; a bettering"),
                new Word("Cousin",Team.NEUTRAL,"The child of a person's uncle or aunt; a first cousin."),
                new Word("Election",Team.NEUTRAL,"A process of choosing a leader, members of parliament, councillors or other representatives by popular vote."),
                new Word("Professor",Team.NEUTRAL,"The most senior rank for an academic at a university or similar institution, informally also known as \"full professor.\" Abbreviated Prof."),
                new Word("Department",Team.NEUTRAL,"A part, portion, or subdivision."),
                new Word("Quality",Team.NEUTRAL,"Level of excellence."),
                new Word("Chocolate",Team.NEUTRAL,"A food made from ground roasted cocoa beans."),
                new Word("Woman",Team.NEUTRAL,"An adult female human."),
                new Word("Family",Team.NEUTRAL,"A group of people who are closely related to one another (by blood, marriage or adoption); kin; for example, a set of parents and their children; an immediate family."),
                new Word("World",Team.NEUTRAL,"(with \"the\") Human collective existence; existence in general."),
                new Word("Finding",Team.NEUTRAL,"To encounter or discover by accident; to happen upon."),
                new Word("Poet",Team.NEUTRAL,"A person who writes poems."),
                new Word("Bread",Team.NEUTRAL,"A foodstuff made by baking dough made from cereals."),
                new Word("Disease",Team.NEUTRAL,"An abnormal condition of a human, animal or plant that causes discomfort or dysfunction; distinct from injury insofar as the latter is usually instantaneously acquired."),
                new Word("Queen",Team.NEUTRAL,"A female monarch. Example: Queen Victoria."),
                new Word("Opportunity",Team.NEUTRAL,"A chance for advancement, progress or profit."),
                new Word("Definition",Team.NEUTRAL,"A statement of the meaning of a word or word group or a sign or symbol (dictionary definitions)."),
                new Word("Description",Team.NEUTRAL,"A sketch or account of anything in words; a portraiture or representation in language; an enumeration of the essential qualities of a thing or species."),
                new Word("Examination",Team.NEUTRAL,"The act of examining."),
                new Word("Basis",Team.NEUTRAL,"A physical base or foundation."),
                new Word("Pizza",Team.NEUTRAL,"A baked Italian dish of a thinly rolled bread dough crust typically topped before baking with tomato sauce, cheese and other ingredients such as meat, vegetables or fruit"),
                new Word("Chest",Team.NEUTRAL,"A box, now usually a large strong box with a secure convex lid.")));
    }

    private synchronized static String generateWord() {
        try {
//            String CN_RANDOM_WORD_API = "https://random-words-api.vercel.app/word/noun";
            String CN_RANDOM_WORD_API = System.getenv("CN_RANDOM_WORD_API");

            System.out.println(CN_RANDOM_WORD_API);

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URL(CN_RANDOM_WORD_API).toURI())
                    .GET()
                    .build();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

            return (String) new JSONArray(getResponse.body()).getJSONObject(0).get("word");
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private synchronized static String obtainDefinition(String word) {
        try {
//            String DEFINITION_API = "https://api.dictionaryapi.dev/api/v2/entries/en/";
            String DEFINITION_API = System.getenv("DEFINITION_API");

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
            e.printStackTrace();
        }
        return null;
    }

//    public static void main(String[] args) {
//        populate();
//        System.out.println(gameWords);
//    }
}
