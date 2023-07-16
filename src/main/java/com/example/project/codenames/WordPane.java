package com.example.project.codenames;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class WordPane extends StackPane {
    // TODO: Add observer here so that when the player switches to SpyMaster, all buttons are set to invisible

    private String style;
    private final Word word;

    public WordPane(Word word) {
        VBox currBox = new VBox();

        Label text = new Label();
        text.setText(word.getWord());
        text.setFont(Font.font("Tw Cen MT Condensed Extra Bold", 16));
        currBox.getChildren().add(text);

//        if (showButton) {
//            Button button = new Button("SELECT");
//            button.setMaxSize(45, 20);
//            button.setFont(Font.font(10));
//            currBox.getChildren().add(button);
//        }

        currBox.setSpacing(3);
        currBox.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(currBox);

        this.style = "-fx-border-color: black; -fx-border-radius: 2;";
        this.setStyle(this.style);

        this.word = word;
    }

    public Word getWord() { return this.word; }

    public void addBackground() {
        this.style += " -fx-background-color: " + this.word.getType().getColor() + ";";
        this.setStyle(this.style);
        ((VBox) this.getChildren().get(0)).getChildren().get(0).setStyle("-fx-text-fill: white;");
    }

    public void addButton() {
        Button button = new Button("SELECT");
        button.setMaxSize(45, 20);
        button.setFont(Font.font(10));
        ((VBox) this.getChildren().get(0)).getChildren().add(button);
    }

    public void selectedUpdate() {
        ((VBox) this.getChildren().get(0)).getChildren().remove(1);
        addBackground();
    }
}
