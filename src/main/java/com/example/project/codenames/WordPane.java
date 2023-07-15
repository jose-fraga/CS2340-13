package com.example.project.codenames;

import com.example.project.codenames.enums.Team;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashSet;

// Model this using the Observer Pattern
public class WordPane extends StackPane {
    private String style;
    private Color color;
    private Word word;


    public void setCurrLength(int currLength) {
        this.currLength = currLength;
    }

    private int currLength;

    public int getRedPosition() {
        return redPosition;
    }

    public void setRedPosition(int redPosition) {
        this.redPosition = redPosition;
    }

    private int redPosition;

    public void setBluePosition(int bluePosition) {
        this.bluePosition = bluePosition;
    }

    public int getBluePosition() {
        return bluePosition;
    }

    private int bluePosition;


    private HashSet<Team> occupants = new HashSet<>(2);

    public WordPane(Word word) {
        VBox currBox = new VBox();

        Label text = new Label();
        text.setText(word.getWord());
        text.setFont(Font.font("Segoe UI Black", 11));

        currBox.getChildren().add(text);
        currBox.getChildren().add(new Button("PRESS"));
        currBox.setSpacing(2);
        currBox.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(currBox);

        this.style = "-fx-border-color: black; -fx-border-radius: 2;";
        this.setStyle(this.style);
        this.word = word;
    }

    public void addBackground() {
        this.style += "-fx-background-color: red;";
        this.setStyle(this.style);
    }

    //each wordpane hashset would have a hashset to determine whether there is a red team or blue team alrady

    public void ToggleOccupants(Team team) {
        if (occupants.contains(team)) {
            occupants.remove(team);
        } else if (!(occupants.contains(team))) {
            occupants.add(team);
        }
    }

    public HashSet<Team> getOccupants() {
        return occupants;
    }

    public int getCurrLength() {
        return currLength;
    }
   // public void SetOccupant(Team team) {
  //      Occupant = team;
  //  }
}
