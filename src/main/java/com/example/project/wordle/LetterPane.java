package com.example.project.wordle;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class LetterPane extends StackPane {
    public AttemptedLetter attemptedLetter;

    public LetterPane(int index) {
        Label curr = new Label();
        curr.setFont(Font.font("Segoe UI Black", 14));
        this.getChildren().add(curr);
        this.setStyle("-fx-border-color: #565758");
        this.attemptedLetter = new AttemptedLetter(index);
    }

    // Hello World
    // Febin Wrote this Code

    public void updateText(String character) {
        ((Label) this.getChildren().get(0)).setText(character);
        this.attemptedLetter.setLetter(character);
    }

    public void updateStyle() {
        this.setStyle("-fx-border-color: black; -fx-background-color:" + attemptedLetter.getStatus().getColor());
        this.getChildren().get(0).setStyle("-fx-text-fill: white");
    }
}
