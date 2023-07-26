package com.example.project.codenames;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;

public class GameLog {
    private final ArrayList<TextFlow> logger;

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

    public void addClueItem(String clue, int count, String color) {
        Text playerItem = new Text("Spymaster");
        playerItem.setFill(Paint.valueOf(color));
        playerItem.setStyle("-fx-font-weight: bold;");

        Text clueItem = new Text(clue.toUpperCase() + " " + count);
        clueItem.setFill(Color.BLUEVIOLET);
        clueItem.setStyle("-fx-font-weight: bold;");

        TextFlow item = new TextFlow(playerItem, new Text(" gives clue "), clueItem);
        logger.add(item);
    }
}
