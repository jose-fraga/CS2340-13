package com.example.project.codenames.controllers;

import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class CNBufferController implements Initializable {
    @FXML private Label bufferLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: Codenames Buffer Screen");
    }

    public void setBufferLabel(String phrase) {
        bufferLabel.setText(phrase);
    }

    @FXML public void switchScreen(ActionEvent e) {
        Helper.changeGameScreen("codenames/CNGameScreen.fxml");
    }
}
