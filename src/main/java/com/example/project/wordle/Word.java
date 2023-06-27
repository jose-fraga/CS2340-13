package com.example.project.wordle;

public class Word {
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return this.word;
    }

    public String letterAt(int index) {
        return this.word.substring(index, index+1);
    }
}
