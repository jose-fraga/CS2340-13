package com.example.project.wordle.controllers;

import com.example.project.Helper;
import com.example.project.wordle.enums.WordDifficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WRLLevelController {
    @FXML
    private void selectLevel(ActionEvent e) {
        int levelNumber = Integer.parseInt(((Button) e.getSource()).getText());
        if (levelNumber == 4) {
            Helper.changeGameScreen(WordDifficulty.FOUR.getPath(), "CENTER");
        } else if (levelNumber == 5) {
            Helper.changeGameScreen(WordDifficulty.FIVE.getPath(), "CENTER");
        } else {
            Helper.changeGameScreen(WordDifficulty.SIX.getPath(), "CENTER");
        }
        Helper.getGamePane().getCenter().requestFocus();
    }
}
