package com.example.project.codenames;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WordPane extends StackPane {
    // TODO: Add observer here so that when the player switches to SpyMaster, all buttons are set to invisible

    private String style;
    private Color color;
    private Word word;

    private boolean isButtonShown;

    public WordPane(Word word, boolean showButton) {
        VBox currBox = new VBox();

        Label text = new Label();
        text.setText(word.getWord());
        text.setFont(Font.font("Tw Cen MT Condensed Extra Bold", 16));
        currBox.getChildren().add(text);

        this.isButtonShown = showButton;
        if (showButton) {
            Button button = new Button("SELECT");
            button.setMaxSize(45, 20);
            button.setFont(Font.font(10));
            currBox.getChildren().add(button);
        }

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
        ((VBox) this.getChildren().get(0)).setStyle("-fx-text-fill: white;");

        if (isButtonShown) {
            ((VBox) this.getChildren().get(0)).getChildren().remove(1);
        }
    }
}
