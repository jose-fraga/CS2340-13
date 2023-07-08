package com.example.project.codenames;

public class Word {
    private String word;
    private boolean isSelected;

    public Word(String word, boolean isRevealed) {
        this.word = word;
        this.isSelected = false;
    }

    public String getWord() { return this.word; }
    public boolean getIsRevealed() { return this.isSelected; }
}
