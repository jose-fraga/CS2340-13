package com.example.project.wordle.controllers;

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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

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
                LetterPane pane = new LetterPane(new TargetWord("APPLE"), j);
                pane.getChildren().add(new Label());
                gridPane.add(pane,j,i);
            }
        }
    }

    @FXML public void handle(KeyEvent e) {
        // Add condition to check if all cells are filled
        if (Character.isLetter(e.getCode().getChar().charAt(0))) {
            if (x == 5) {
                return;
            }
            if (gridPane.getChildren().get(cellIdx) instanceof Group) {
                cellIdx++;
            }
            LetterPane pane = (LetterPane) gridPane.getChildren().get(cellIdx);
            ((Label) pane.getChildren().get(0)).setText(e.getCode().getChar());
            cellIdx++;
            x++;
            currWord += e.getCode().getChar();
        } else if (e.getCode() == KeyCode.BACK_SPACE) {
            if (x == 0 || gridPane.getChildren().get(cellIdx) instanceof Group) {
                return;
            }
            LetterPane pane = (LetterPane) gridPane.getChildren().get(--cellIdx);
            ((Label) pane.getChildren().get(0)).setText("");
            x--;
            currWord = currWord.substring(0,currWord.length()-1);
        } else if (e.getCode() == KeyCode.ENTER) {
            if (x != 5) {
                return;
            }
            System.out.println(currWord);
            currWord = "";
            x = 0;
            y = Math.min(++y, (gridPane.getRowCount() - 1));
        }
    }

//        private String correctAnswer = DictionaryService.generateWord(5);
//        public int currentRow = 0;
//
//        public void checkAnswer(char[] userAnswer) {
//            String answer = new String(userAnswer);
//            if (DictionaryService.checkValidity(answer)) {
//                for (int column = 0; column < answer.length(); column++) {
//                    String letter = correctAnswer.substring(column, column + 1);
//                    if (letter.equals(correctAnswer.substring(column, column + 1))) {
//                        changeColor("green", currentRow, column);
//                    } else if (correctAnswer.contains(letter)) {
//                        changeColor("yellow", currentRow, column);
//                    } else {
//                        changeColor("white", currentRow, column);
//                    }
//                }
//                currentRow++;
//            } else {
//                System.out.println("Word is not valid");
//            }
//        }
}
