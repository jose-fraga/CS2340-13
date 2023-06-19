package com.example.project.flowfree.controllers;

import com.example.project.Game;
import com.example.project.Helper;
import com.example.project.Main;
import com.example.project.flowfree.FFGame;
import com.example.project.flowfree.enums.LevelDifficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FFLevelController implements Initializable {
    @FXML private BorderPane levelPane;
    @FXML private HBox gameControls;
    @FXML private Button toggleButton, returnButton;
    @FXML private Label timerDisplay;

    private FFGame gameInstance;
    private final String SCREEN_GAME_EASY = LevelDifficulty.EASY.path();
    private final String SCREEN_LEVEL_SELECT = "flowfree/FFLevelScreen.fxml";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        this.gameInstance = FFGame.getGameInstance();
    }

    @FXML private void selectLevel(ActionEvent e) throws IOException {
        int levelNumber = Integer.parseInt(((Button) e.getSource()).getText()) - 1;
        this.gameInstance.createLevel(levelNumber);
        Helper.changeGameScreen(SCREEN_GAME_EASY);
    }
}
