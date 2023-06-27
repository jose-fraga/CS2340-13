package com.example.project.wordle;

import java.util.HashMap;

public class TargetWord extends Word {
    private HashMap<String, Integer> letterCount = new HashMap<String, Integer>();

    public TargetWord(String word) {
        super(word);
        for (int i = 0; i < word.length(); i++) {
            String letter = word.substring(i, i+1);
            int count = letterCount.containsKey(letter) ? letterCount.get(letter) : 0;
            letterCount.put(letter, ++count);
        }
    }

    public boolean hasLetter(String letter) {
        return letterCount.containsKey(letter);
    }
}
