package com.example.project.wordle.controllers;

import com.example.project.Game;
import com.example.project.Helper;
import com.example.project.wordle.AttemptedLetter;
import com.example.project.wordle.DictionaryService;
import com.example.project.wordle.LetterPane;
import com.example.project.wordle.Life;
import com.example.project.wordle.TargetWord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class WRLGameController implements Initializable {
    @FXML private GridPane gridPane;
    @FXML private Label endLabel;
    @FXML private Label messageLabel;
    @FXML private Label livesDisplay;

    private int x = 0, y = 0, cellIdx = 0;

    // Update this with AttemptedWord
    private String currWord;
    private DictionaryService dictionaryService = new DictionaryService();
    private String targetWord = "";

    private Life life;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populate();
        currWord = "";
        life = new Life(); // Instantiate Life
    }

    private void populate() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                gridPane.add(new LetterPane(j), j, i);
            }
        }
        targetWord = dictionaryService.generateWord(gridPane.getColumnCount()).toUpperCase();
    }

    @FXML
    public void handle(KeyEvent e) {
        // Check if game over
        if (life.getLives() <= 0) {
            gameOver();
            return;
        }

        // Add condition to check if all cells are filled
        int rowLength = gridPane.getColumnCount();
        if (Character.isLetter(e.getCode().getChar().charAt(0))) {
            if (x == rowLength) {
                return;
            }
            if (gridPane.getChildren().get(cellIdx) instanceof Group) {
                cellIdx++;
            }
            ((LetterPane) gridPane.getChildren().get(cellIdx)).updateText(e.getCode().getChar());
            cellIdx++;
            x++;
            currWord += e.getCode().getChar();

            // Check if game over
            if (life.getLives() <= 0) {
                gameOver();
            }
        } else if (e.getCode() == KeyCode.BACK_SPACE) {
            if (x == 0 || gridPane.getChildren().get(cellIdx) instanceof Group) {
                return;
            }
            ((LetterPane) gridPane.getChildren().get(--cellIdx)).updateText("");
            x--;
            currWord = currWord.substring(0,currWord.length()-1);
        } else if (e.getCode() == KeyCode.ENTER) {
            if (x != rowLength) {
                return;
            }
            life.calculateLives(targetWord, currWord);
            livesDisplay.setText("Lives: " + life.getLives());
            gridPane.getChildren().subList(cellIdx - rowLength, cellIdx).forEach(item -> {
                ((LetterPane) item).attemptedLetter.checkAttempt(targetWord);
                ((LetterPane) item).updateStyle();
            });
            currWord = "";
            x = 0;
            y = Math.min(++y, rowLength);
          
            // Check if game over
            if (life.getLives() <= 0) {
                gameOver();
            }
        }
    }


    private void gameOver() {
        Helper.changeGameScreen("wordle/WRLEndLostScreen.fxml");
        endLabel.setText("You lost");
        endLabel.setVisible(true);
        messageLabel.setVisible(false);
    }

}
