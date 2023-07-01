package com.example.project.wordle.controllers;

import com.example.project.Game;
import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WRLEndController implements Initializable {
    @FXML private Label endLabel;
    @FXML private Label messageLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: " + Helper.currentGame.title() + "End Screen");
    }

    public void showFailureMessage() {
        endLabel.setText("FAILURE!");
        endLabel.setStyle("-fx-background-color: red; -fx-border-color: black");
        messageLabel.setText("Sorry! Ran out of lives!");
    }

    @FXML public void returnToLevelSelect(ActionEvent e) {
        Helper.changeGameScreen(Helper.currentGame.gameFxmlPath());
    }

    @FXML public void quitGame(ActionEvent e) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }
}
