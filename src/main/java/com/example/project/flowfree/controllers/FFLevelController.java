package com.example.project.flowfree.controllers;

import com.example.project.Helper;
import com.example.project.flowfree.FFGame;
import com.example.project.flowfree.enums.LevelDifficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FFLevelController implements Initializable {
    private FFGame gameInstance;
    // Hardcoded Values --> Change in Sprint #5
    private final String SCREEN_GAME_EASY = LevelDifficulty.EASY.getPath();
    private final String SCREEN_LEVEL_SELECT = "flowfree/FFLevelScreen.fxml";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.gameInstance = FFGame.getGameInstance();
    }

    @FXML private void selectLevel(ActionEvent e) throws IOException {
        int levelNumber = Integer.parseInt(((Button) e.getSource()).getText()) - 1;
        this.gameInstance.createLevel(levelNumber);
        Helper.changeGameScreen(SCREEN_GAME_EASY, "CENTER");
    }
}
