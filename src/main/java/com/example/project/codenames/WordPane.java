package com.example.project.codenames;

import com.example.project.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class WordPane extends StackPane {
    private Color color;
    private Word word;
//    private ArrayList<Player> playerGuesses;

    public WordPane(Word word) {
        Label curr = new Label();
        curr.setText(word.getWord());
        curr.setFont(Font.font("Segoe UI Black", 14));
        this.getChildren().add(curr);

        Button select = new Button("PRESS");
        this.getChildren().add(select);

        this.setStyle("-fx-border-color: black");
        this.word = word;
    }

    public void changeBackground() {
        this.setStyle("-fx-background-color: " + Color.RED);
    }
}
