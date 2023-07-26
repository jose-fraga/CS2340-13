package com.example.project.codenames.controllers.components;

import com.example.project.codenames.CNGame;
import com.example.project.codenames.Round;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CNClueBoxController implements Initializable {
    @FXML private Label clueDisplay, countDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Round currRound = CNGame.getGameInstance().getRound();
        String style = "-fx-border-radius: 3; -fx-border-color: " + currRound.getActiveTeam().getType().getColor();

        clueDisplay.setStyle(style);
        clueDisplay.setText(currRound.getCurrentClue());

        countDisplay.setStyle(style);
        countDisplay.setText(String.valueOf(currRound.getCurrentGuessLimit() - 1));
    }

    @FXML private void switchTeams(ActionEvent e) {
        CNGame.getGameInstance().getRound().endTurn();
    }
}
