package com.example.project.codenames.controllers;

import com.example.project.Game;
import com.example.project.Helper;
import com.example.project.codenames.CNGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class CNEndScreenController implements Initializable {
    @FXML public Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: " + Helper.currentGame.title() + "End Screen");
        changeLabel();
    }

    @FXML public void quitGame(ActionEvent e) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }

     public void changeLabel() {
        label.setText("" + CNGame.getGameInstance().getRound().getActiveTeam().getType());
    }




}
