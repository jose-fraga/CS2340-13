package com.example.project.wordle.controllers;

import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WRLEndController implements Initializable {
    @FXML private Label endLabel, messageLabel, scoreDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: Wordle End Screen");
    }

    public void showFailureMessage() {
        endLabel.setText("FAILURE!");
        endLabel.setStyle("-fx-background-color: red; -fx-border-color: black");
        messageLabel.setText("Sorry! Ran out of lives!");
    }

    public void updateScore(String score) {
        scoreDisplay.setText(score);
    }

    @FXML private void continueToLeaderboard(ActionEvent e) {
        Helper.changeGameScreen("wordle/WRLLeaderboardScreen.fxml");
    }
}
