package com.example.project.wordle;

import com.example.project.Game;
import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WRLSplashScreenController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: Wordle Splash Screen");
    }

    @FXML private void returnToSelect(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "SelectScreen.fxml", "CS2340 Project");
    }

    @FXML private void continueToConfigure(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.WORDLE.configFxmlPath(), "CS2340 Configuration");
    }
}
