package com.example.project.codenames.controllers;

import com.example.project.Game;
import com.example.project.Helper;
import com.example.project.codenames.enums.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CNEndController implements Initializable {
    @FXML private Label teamDisplay, scoreDisplay;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: " + Helper.currentGame.title() + "End Screen");
    }

    public void updateScreen(Type winningTeam, int score) {
        teamDisplay.setText(winningTeam + " Team Wins!");
        teamDisplay.setStyle("-fx-border-color: black; -fx-background-color: " + winningTeam.getColor());
        scoreDisplay.setText("Score: " + (score + 12));
    }

    @FXML public void restartGame(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "GameScreen.fxml", "CS2340 - Codenames (Game)");
    }

    @FXML public void quitGame(ActionEvent e) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }
}
