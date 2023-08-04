package com.example.project.wordle.controllers;

import com.example.project.Helper;
import com.example.project.wordle.enums.WordDifficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WRLLevelController {
    @FXML
    private void selectLevel(ActionEvent e) {
        int levelNumber = Integer.parseInt(((Button) e.getSource()).getText());
        if (levelNumber == 4) {
            Helper.changeGameScreen(WordDifficulty.FOUR.getPath());
        } else if (levelNumber == 5) {
            Helper.changeGameScreen(WordDifficulty.FIVE.getPath());
        } else {
            Helper.changeGameScreen(WordDifficulty.SIX.getPath());
        }
        Helper.getGamePane().getCenter().requestFocus();
    }
}
