package com.example.project.wordle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Leaderboard {
    private volatile static Leaderboard instance;
    private final Map<String, Integer> playerList;

    private Leaderboard() {
        playerList = new LinkedHashMap<>();
    }

    public static synchronized Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }

    public void addEntry(String name, int score) {
        playerList.put(name, score);
    }

    public List<Map.Entry<String,Integer>> getTopScores() {
        List<Map.Entry<String,Integer>> list = new ArrayList<>(playerList.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);
        playerList.clear();
        for (Map.Entry<String, Integer> item : list) {
            playerList.put(item.getKey(), item.getValue());
        }
        return list;
    }
}
