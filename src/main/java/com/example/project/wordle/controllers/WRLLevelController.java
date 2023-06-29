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

public class WRLLevelController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void selectLevel(ActionEvent e) throws IOException {
        int levelNumber = Integer.parseInt(((Button) e.getSource()).getText());
        System.out.println(levelNumber);
        if (levelNumber == 4) {
            Helper.changeGameScreen(WordDifficulty.FOUR.getPath());
            Helper.getGamePane().getCenter().requestFocus();
        } else if (levelNumber == 5) {
            Helper.changeGameScreen(WordDifficulty.FIVE.getPath());
            Helper.getGamePane().getCenter().requestFocus();
        } else {
            Helper.changeGameScreen(WordDifficulty.SIX.getPath());
            Helper.getGamePane().getCenter().requestFocus();
        }
    }
}
