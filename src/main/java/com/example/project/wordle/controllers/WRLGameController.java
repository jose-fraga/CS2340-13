package com.example.project.wordle.controllers;

import com.example.project.Helper;
import com.example.project.wordle.AttemptedWord;
import com.example.project.wordle.LetterPane;
import com.example.project.wordle.Life;
import com.example.project.wordle.TargetWord;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WRLGameController implements Initializable {
    @FXML private GridPane gridPane;
    @FXML private Label warningLabel;
    @FXML private Label livesDisplay;

    private int x = 0, y = 0, cellIdx = 0;
    private AttemptedWord currWord;
    private TargetWord targetWord;
    private Life life;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populate();
        currWord = new AttemptedWord("");
        targetWord = new TargetWord(gridPane.getColumnCount());
        life = new Life(); // Instantiate Life
    }

    private void populate() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                gridPane.add(new LetterPane(j), j, i);
            }
        }
    }

    @FXML
    public void handle(KeyEvent e) {
        warningLabel.setVisible(false);
        // Check if game over
        if (life.getLives() <= 0) {
            switchToEndScreen(true);
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
            currWord.add(e.getCode().getChar());
            if (life.getLives() <= 0) {
                switchToEndScreen(true);
            }
        } else if (e.getCode() == KeyCode.BACK_SPACE) {
            if (x == 0 || (cellIdx <= gridPane.getChildren().size()-1 && gridPane.getChildren().get(cellIdx) instanceof Group)) {
                return;
            }
            ((LetterPane) gridPane.getChildren().get(--cellIdx)).updateText("");
            x--;
            currWord.remove();
        } else if (e.getCode() == KeyCode.ENTER) {
            if (x != rowLength) {
                return;
            }
            if (currWord.isValid()) {
                if (currWord.compareWithTarget(targetWord)) {
                    switchToEndScreen(false);
                } else {
                    life.calculateLives(targetWord.getWord(), currWord.getWord());
                    livesDisplay.setText("Lives: " + life.getLives());
                    System.out.println(targetWord.getWord());
                }
            } else {
                warningLabel.setVisible(true);
                return;
            }
            gridPane.getChildren().subList(cellIdx - rowLength, cellIdx).forEach(item -> {
                ((LetterPane) item).attemptedLetter.checkAttempt(targetWord.getWord());
                ((LetterPane) item).updateStyle();
            });
            currWord = new AttemptedWord("");
            x = 0;
            y = Math.min(++y, rowLength);
            if (life.getLives() <= 0) {
                switchToEndScreen(true);
            }
        }
    }

    private void switchToEndScreen(boolean isFailure) {
        FXMLLoader loader = safelyChangeScreen("wordle/WRLEndScreen.fxml");
        if (isFailure) {
            WRLEndController controller = loader.getController();
            controller.showFailureMessage();
        }
    }

    private FXMLLoader safelyChangeScreen(String fxmlPath) {
        return Helper.changeGameScreen(fxmlPath);
    }
}
