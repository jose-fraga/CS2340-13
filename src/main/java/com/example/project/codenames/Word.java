package com.example.project.codenames;

import com.example.project.codenames.enums.Type;

// TODO: make words observable so that we can take actions when it is selected.
public class Word {
    private final String word;
    private final String definition;
    private Type type;
    private boolean isSelected;

    public Word(Type type, String word, String definition) {
        this.word = word;
        this.definition = definition;
        this.type = type;
        this.isSelected = false;
    }

    public String getWord() { return this.word; }

    public boolean getIsSelected() { return this.isSelected; }

    public Type getType() { return this.type; }
    public void setType(Type type) { this.type = type; }

    @Override
    public String toString() {
        return "new Word(\"" + word + "\"," + type + ", " + definition + "\")";
    }
}
