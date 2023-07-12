package com.example.project.codenames;

public class Word {
    private String word;
    private boolean isSelected;
    private String definition;

    public Word(String word, boolean isRevealed, String definition) {
        this.word = word;
        this.isSelected = false;
        this.definition = definition;
    }

    public String getWord() { return this.word; }
    public boolean getIsRevealed() { return this.isSelected; }

    @Override
    public String toString() {
        return "new Word(\"" + word + "\",false,\"" + definition + "\")";
    }
}
