package com.example.project.flowfree.controllers;

import com.example.project.Helper;
import com.example.project.flowfree.FFGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class FFLevelController implements Initializable {
    private FFGame gameInstance;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.gameInstance = FFGame.getGameInstance();
    }

    @FXML private void selectLevel(ActionEvent e) {
        int levelNumber = Integer.parseInt(((Button) e.getSource()).getText())-1;
        this.gameInstance.setLevel(levelNumber);
        // Update using enums for other difficulties
        Helper.changeGameScreen("flowfree/levels/Easy.fxml");
    }
}
