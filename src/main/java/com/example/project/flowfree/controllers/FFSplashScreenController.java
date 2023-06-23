package com.example.project.flowfree.controllers;

import com.example.project.Game;
import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FFSplashScreenController implements Initializable {
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("ENTERED: Flow Free Splash Screen");
    }

    @FXML public void backToGameSelect(ActionEvent e) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }

    @FXML private void configureGame(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.FLOW.configFxmlPath(), "CS2340 - " + Helper.currentGame.title() + " (Configuration)");
    }
}
