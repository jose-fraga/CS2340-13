package com.example.project.codenames.controllers;

import com.example.project.Helper;
import javafx.event.ActionEvent;

public class CNBufferScreenController {
    public void switchScreen(ActionEvent e) {
        Helper.changeGameScreen("codenames/CNGameScreen.fxml");
    }
}
