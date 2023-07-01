package com.example.project.wordle;

import com.example.project.wordle.enums.Status;

import java.util.ArrayList;
import java.util.HashMap;

public class TargetWord extends Word {
    private HashMap<String, Letter> letterMap = new HashMap<>();

    public TargetWord(int length) {
        super("");
        updateWord(length);
        populateLetterMap();
    }

    private void updateWord(int length) {
        String curr = DictionaryService.generateWord(length).toUpperCase();
        while (!DictionaryService.checkValidity(curr)) {
            curr = DictionaryService.generateWord(length);
        }
        this.word = curr;
    }

    private void populateLetterMap() {
        for (int i = 0; i < this.word.length(); i++) {
            String currKey = getLetter(i);
            Letter currentLetter = letterMap.get(currKey);

            if (currentLetter == null) {
                currentLetter = new Letter(currKey, i);
                letterMap.put(currKey, currentLetter);
            } else {
                currentLetter.count++;
                currentLetter.indices.add(i);
            }
        }
    }

    public ArrayList<Status> check(Word attempt) {
        ArrayList<Status> result = new ArrayList<Status>();
        for (int i = 0; i < attempt.word.length(); i++) {
            Letter letterStats = letterMap.get(i);
            result.add(Status.INCORRECT);
        }
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private class Letter {
        private String letter;
        protected int count;
        protected ArrayList<Integer> indices = new ArrayList<>();
        protected ArrayList<Integer> tempIndices = new ArrayList<>();

        protected Letter(String letter, int index) {
            this.letter = letter;
            this.count = 1;
            this.indices.add(index);
            resetCheck();
        }

        private void resetCheck() {
            this.tempIndices = new ArrayList<Integer>(indices.size());
            for(Integer i : indices){
                this.tempIndices.add(Integer.valueOf(i));
            }
        }
    }
}
