package com.example.project.codenames;

import com.example.project.Game;
import com.example.project.Helper;
import com.example.project.codenames.controllers.CNController;
import com.example.project.wordle.controllers.WRLEndController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class CNSpyScreen {
    @FXML
    private void endTurn(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "codenames/CNBufferScreen.fxml", "CS2340 - " + Helper.currentGame.title() + " (Splash)");
    }
}
