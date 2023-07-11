package com.example.project.codenames;

import com.example.project.codenames.enums.Team;

public class Word {
    private String word;
    private boolean isSelected;
    private Team type;

    public Word(String word, Team type) {
        this.word = word;
        this.type = type;
        this.isSelected = false;
    }

    public String getWord() { return this.word; }
    public boolean getIsRevealed() { return this.isSelected; }
    public void select() { this.isSelected = true; }

    public Team getType() {
        return type;
    }


    //TODO: make words observable so that we can take actions when it is selected.
}
