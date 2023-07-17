package com.example.project.codenames;

import com.example.project.codenames.enums.Type;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// TODO: make words observable so that we can take actions when it is selected.
public class Word {
    private final String word;
    private final String definition;
    private Type type;
    private boolean isSelected;
    private PropertyChangeSupport support;

    public Word(Type type, String word, String definition) {
        this.support = new PropertyChangeSupport(this);
        this.word = word;
        this.definition = definition;
        this.type = type;
        this.isSelected = false;
    }


    public String getWord() { return this.word; }
    public String getDefinition() { return this.definition; }

    public boolean getIsSelected() { return this.isSelected; }

    public void select() {
        this.isSelected = true;
        this.support.firePropertyChange("isSelected", false, true);
    }

    public Type getType() { return this.type; }
    public void setType(Type type) { this.type = type; }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        return "new Word(\"" + word + "\"," + type + ", " + definition + "\")";
    }
}
