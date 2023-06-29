package com.example.project.wordle.controllers;

import com.example.project.wordle.AttemptedLetter;
import com.example.project.wordle.DictionaryService;
import com.example.project.wordle.LetterPane;
import com.example.project.wordle.TargetWord;
import javafx.fxml.FXML;
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

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class WRLGameController implements Initializable {
    @FXML private GridPane gridPane;

    private int x = 0, y = 0, cellIdx = 0;

    // Update this with AttemptedWord
    private String currWord;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populate();
        currWord = "";
    }

    private void populate() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                gridPane.add(new LetterPane(j), j, i);
            }
        }
    }

    @FXML public void handle(KeyEvent e) {
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
            gridPane.getChildren().subList(cellIdx - rowLength, cellIdx).forEach(item -> {
                ((LetterPane) item).attemptedLetter.checkAttempt(rowLength);
                ((LetterPane) item).updateStyle();
            });
            currWord = "";
            x = 0;
            y = Math.min(++y, rowLength);
        }
    }
}
