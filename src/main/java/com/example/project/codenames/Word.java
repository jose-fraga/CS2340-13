package com.example.project.codenames;

import com.example.project.codenames.enums.Team;

// TODO: make words observable so that we can take actions when it is selected.
public class Word {
    private String word;
    private boolean isSelected;
    private String definition;
    private Team type;

    public Word(String word, Team type, String definition) {
        this.word = word;
        this.type = type;
        this.isSelected = false;
        this.definition = definition;
    }

    public String getWord() { return this.word; }
    public boolean getIsRevealed() { return this.isSelected; }

    public void setType(Team type) { this.type = type; }

    public void select() { this.isSelected = true; }

    public Team getType() {
        return type;
    }

    @Override
    public String toString() {
        return "new Word(\"" + word + "\",false,\"" + definition + "\")";
    }
}
