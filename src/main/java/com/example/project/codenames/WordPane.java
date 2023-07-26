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
import java.util.HashSet;

public class WordPane extends StackPane {
    private final Word word;

    private String style;
    private int currLength, redPosition, bluePosition;

    private boolean isGuessed;

    private final HashSet<Type> occupants = new HashSet<>(2);

    public void setCurrLength(int currLength) { this.currLength = currLength; }


    public int getRedPosition() { return this.redPosition; }
    public void setRedPosition(int redPosition) { this.redPosition = redPosition; }


    public int getBluePosition() { return this.bluePosition; }
    public void setBluePosition(int bluePosition) { this.bluePosition = bluePosition; }

    public Word getWord() { return this.word; }

    public HashSet<Type> getOccupants() { return this.occupants; }

    public int getCurrLength() { return this.currLength; }

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

        setTooltip();
    }

    public void addBackground() {
        this.style += " -fx-background-color: " + this.word.getType().getColor() + ";";
        if (this.word.getIsSelected()) {
            this.style += "-fx-border-width: 2; -fx-border-style: solid inside; -fx-border-color: #00000066; -fx-border-insets: 0;";
            this.style += "-fx-opacity: 0.8;";
        } else {
            this.style += "-fx-border-width: 3; -fx-border-style: solid inside; -fx-border-color: #00000066; -fx-border-insets: 0;";
        }
        this.setStyle(this.style);

        String cardTextStyle = "-fx-text-fill: " + ((word.getType() == Type.ASSASSIN) ? "#ffffffcc;" : "#000000cc");
        ((VBox) this.getChildren().get(0)).getChildren().get(0).setStyle(cardTextStyle);
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

    private void setTooltip() {
        Tooltip tooltip = new Tooltip(word.getDefinition());
        tooltip.setMinWidth(50);
        tooltip.setMaxWidth(400);
        tooltip.setWrapText(true);
        tooltip.setTextAlignment(TextAlignment.CENTER);
        tooltip.setFont(Font.font("Tw Cen MT Condensed Extra Bold"));
        tooltip.setStyle("-fx-background-color: dimgray; -fx-text-fill: white;");
        Tooltip.install(this, tooltip);
    }

    public void ToggleOccupants(Type team) {
        if (occupants.contains(team)) {
            occupants.remove(team);
        } else {
            occupants.add(team);
        }
    }

    public boolean hasOccupant(Type team) { return this.occupants.contains(team); }
}
