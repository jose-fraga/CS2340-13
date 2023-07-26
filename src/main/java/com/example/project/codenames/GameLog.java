package com.example.project.codenames;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

// Source: https://www.youtube.com/watch?v=icf5S9fzRXE
// We followed this tutorial to understand how to implement the Observer Pattern
// through using the PropertyChangeListener and PropertyChangeSupport. This
// code successfully adds listeners (observers), assigns support (observable),
// and notifies listeners when some property changes.
public class GameLog implements PropertyChangeListener {
    private final ArrayList<TextFlow> logger;
    private String color;

    public GameLog() {
        this.logger = new ArrayList<>();
    }

    public ArrayList<TextFlow> getLogItems() { return this.logger; }

    public void addLogItem(Word word, String color) {
        Text playerItem = new Text("Operative");
        playerItem.setFill(Paint.valueOf(color));
        playerItem.setStyle("-fx-font-weight: bold;");

        TextFlow item;
        if (word != null) {
            Text wordItem = new Text(word.getWord().toUpperCase());
            wordItem.setFill(Paint.valueOf(word.getType().getColor()));
            wordItem.setStyle("-fx-font-weight: bold;");

            item = new TextFlow(playerItem, new Text(" taps "), wordItem);
        } else {
            item = new TextFlow(playerItem, new Text(" ends guessing "));
        }
        logger.add(item);
    }

    public void addClueItem(String clue, int count) {
        Text playerItem = new Text("Spymaster");
        playerItem.setFill(Paint.valueOf(this.color));
        playerItem.setStyle("-fx-font-weight: bold;");

        Text clueItem = new Text(clue.toUpperCase() + " " + count);
        clueItem.setFill(Color.BLUEVIOLET);
        clueItem.setStyle("-fx-font-weight: bold;");

        TextFlow item = new TextFlow(playerItem, new Text(" gives clue "), clueItem);
        logger.add(item);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt);
        this.color = (String) evt.getNewValue();
    }
}
