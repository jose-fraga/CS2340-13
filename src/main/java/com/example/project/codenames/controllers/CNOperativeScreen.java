package com.example.project.codenames.controllers;

import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CNOperativeScreen {
    @FXML
    private void endTurn(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "codenames/CNBufferScreen.fxml",
                "CS2340 - " + Helper.currentGame.title() + " (Splash)");
    }
}