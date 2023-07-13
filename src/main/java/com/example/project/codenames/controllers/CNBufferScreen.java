package com.example.project.codenames.controllers;

import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CNBufferScreen {
    static int count = 0;

    public void switchScreen(ActionEvent e) {
        if (count % 2 == 0) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Helper.changeScreen(stage, "codenames/CNOperativeScreen.fxml",
                    "CS2340 - " + Helper.currentGame.title() + " (Splash)");
            count++;
        } else {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Helper.changeScreen(stage, "codenames/CNSpyScreen.fxml",
                    "CS2340 - " + Helper.currentGame.title() + " (Splash)");
            count++;
        }
    }
}
