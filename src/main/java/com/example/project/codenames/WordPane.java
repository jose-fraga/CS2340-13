package com.example.project.codenames;

import com.example.project.codenames.enums.Type;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class WordPane extends StackPane {
    private final Word word;

    private String style;
    private boolean isGuessed, isSelected;

    public WordPane(Word word) {
        this.word = word;
        VBox currBox = new VBox();

        Label text = new Label();
        text.setText(word.getWord());
        text.setFont(Font.font("Tw Cen MT Condensed Extra Bold", 16));
        currBox.getChildren().add(text);

        currBox.setSpacing(3);
        currBox.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(currBox);

        this.style = "-fx-border-color: black;";
        this.setStyle(this.style);

        this.isGuessed = false;
        this.isSelected = false;

        setTooltip();
    }

    public Word getWord() { return this.word; }

    public void addBackground() {
        String genericStyle = "-fx-border-style: solid inside; -fx-border-color: #00000066; -fx-border-insets: 0; ";
        this.style += " -fx-background-color: " + this.word.getType().getColor() + ";";

        if (this.word.getIsSelected()) {
            this.style += "-fx-border-width: 2; " + genericStyle + "-fx-opacity: 0.8;";
        } else {
            this.style += "-fx-border-width: 3; " + genericStyle;
        }
        this.setStyle(this.style);

        String cardTextStyle = "-fx-text-fill: " + ((this.word.getType() == Type.ASSASSIN) ? "#ffffffcc;" : "#000000cc");
        ((VBox) this.getChildren().get(0)).getChildren().get(0).setStyle(cardTextStyle);
    }

    public void addButton() {
        Button button = new Button("SELECT");
        button.setMaxSize(45, 20);
        button.setFont(Font.font(10));
        ((VBox) this.getChildren().get(0)).getChildren().add(button);
    }

    public void handleGuess() {
        if (!this.isSelected && !this.isGuessed) {
            this.isGuessed = true;

            Round round = CNGame.getGameInstance().getRound();

            Label guessLabel = new Label((round.getActiveTeam().getType() == Type.RED) ? "Red" : "Blue");
            guessLabel.setStyle(
                    "-fx-text-fill: white;" +
                            "-fx-background-color: " + round.getActiveTeam().getType().getColor() + ";" +
                            "-fx-border-color: " + round.getActiveTeam().getType().getColor());
            guessLabel.setPrefWidth(30);
            guessLabel.setAlignment(Pos.CENTER);
            guessLabel.setFont(Font.font("Tw Cen MT Condensed Extra Bold", 14));

            ((VBox) this.getChildren().get(0)).getChildren().add(guessLabel);
        } else if (!isSelected || this.isGuessed) {
            this.isGuessed = false;
            ((VBox) this.getChildren().get(0)).getChildren().remove(2);
        }
    }

    public void selectedUpdate() {
        this.isSelected = true;
        handleGuess();
        ((VBox) this.getChildren().get(0)).getChildren().remove(1);
        addBackground();
    }

    private void setTooltip() {
        Tooltip tooltip = new Tooltip(this.word.getDefinition());
        tooltip.setMinWidth(50);
        tooltip.setMaxWidth(400);
        tooltip.setWrapText(true);
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setFont(Font.font("Tw Cen MT Condensed Extra Bold"));
        tooltip.setStyle("-fx-background-color: dimgray; -fx-text-fill: white;");
        Tooltip.install(this, tooltip);
    }
}
