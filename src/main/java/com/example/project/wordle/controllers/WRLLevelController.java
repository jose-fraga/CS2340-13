package com.example.project.wordle.controllers;

import com.example.project.Helper;
import com.example.project.wordle.enums.WordDifficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WRLLevelController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void selectLevel(ActionEvent e) throws IOException {
        Helper.changeGameScreen(WordDifficulty.FIVE.getPath());
        Helper.getGamePane().getCenter().requestFocus();
    }
}
