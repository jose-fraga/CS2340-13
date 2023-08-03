package com.example.project.wordle;

import java.util.HashMap;

public class Word {
    protected String word;
    private final HashMap<Character, Integer> letterMap = new HashMap<>();

    public Word(String word) {
        this.word = word;
    }

    public String getWord() { return this.word; }

    public HashMap<Character, Integer> getLetterMap() { return this.letterMap; }

    public void add(String word) { this.word += word; }
    public void remove() { this.word = this.word.substring(0,this.word.length()-1); }

    public boolean isValid() {
        return DictionaryService.checkValidity(this.word);
    }

    public boolean compare(Word targetWord) {
        return this.word.equals(targetWord.getWord());
    }

    public void updateLetterMap(Character curr) {
        int oldVal = letterMap.get(curr);
        if ((--oldVal) <= 0) {
            this.letterMap.remove(curr);
        } else {
            this.letterMap.replace(curr, --oldVal);
        }
    }

    public void populateLetterMap() {
        this.letterMap.clear();
        for (int i = 0; i < this.word.length(); i++) {
            if (!letterMap.containsKey(this.word.charAt(i))) {
                letterMap.put(this.word.charAt(i), 1);
            } else {
                int oldVal = letterMap.get(this.word.charAt(i));
                letterMap.replace(this.word.charAt(i), oldVal, ++oldVal);
            }
        }
    }
}
