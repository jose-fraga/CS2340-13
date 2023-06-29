package com.example.project.wordle;

public class TargetWord extends Word {

    public TargetWord(String word) {
        super(word);
    }

    public AttemptedWord check(AttemptedWord attemptedWord) {
        return null;
    }

    public String getLetter(int index) {
        String result = "";
        if (index >= 0 && index < this.word.length()) {
            result = this.word.charAt(index) + "";
        }
        return result;
    }
}
