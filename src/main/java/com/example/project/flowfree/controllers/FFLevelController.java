package com.example.project.flowfree.controllers;

import com.example.project.Helper;
import com.example.project.flowfree.FFGame;
import com.example.project.flowfree.Level;
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
    private LevelDifficulty gameLevel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.gameInstance = FFGame.getGameInstance();
    }

    @FXML private void selectLevel(ActionEvent e) throws IOException {
        int levelNumber = Integer.parseInt(((Button) e.getSource()).getText());
        if (levelNumber >= 1 && levelNumber <= 3) {
            gameLevel = LevelDifficulty.EASY;
            levelNumber -= 1;
        } else if (levelNumber >= 4 && levelNumber <= 6) {
            gameLevel = LevelDifficulty.MEDIUM;
            levelNumber -= 4;
        } else if (levelNumber >= 7 && levelNumber <= 9) {
            gameLevel = LevelDifficulty.HARD;
            levelNumber -= 7;
        }
        this.gameInstance.createLevel(levelNumber);
        Level.TIME_LIMIT = gameLevel.getTimerLimit();
        this.gameInstance.setLevelGrid(gameLevel);
        Helper.changeGameScreen(gameLevel.getPath());
    }
}
