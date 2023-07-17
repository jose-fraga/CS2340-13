package com.example.project.codenames.controllers;

import com.example.project.codenames.CNGame;
import com.example.project.codenames.Round;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CNClueBoxController implements Initializable {
    @FXML private Label clueWordDisplay;
    @FXML private Label clueGuessDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Round currRound = CNGame.getGameInstance().getRound();
        String style = "-fx-border-radius: 3; -fx-border-color: " + currRound.getActiveTeam().getType().getColor() + ";";

        clueWordDisplay.setStyle(style);
        clueGuessDisplay.setStyle(style);

        clueWordDisplay.setText(currRound.getCurrentClue());
        clueGuessDisplay.setText(String.valueOf(currRound.getCurrentGuessLimit() - 1));
    }

    @FXML private void switchTeams(ActionEvent e) {
        CNGame.getGameInstance().getRound().endTurn();
    }
}
