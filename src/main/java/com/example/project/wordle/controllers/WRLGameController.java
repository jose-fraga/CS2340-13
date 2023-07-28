package com.example.project.wordle.controllers;

import com.example.project.Helper;
import com.example.project.wordle.*;
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
    @FXML private Label warningLabel, livesDisplay;

    private int x = 0, y = 0, cellIdx = 0;
    private Word currWord;
    private TargetWord targetWord;
    private Life life;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populate();
        currWord = new AttemptedWord("");
        targetWord = new TargetWord(gridPane.getColumnCount());
        life = WRLGame.getInstance().getLife(); // Use the Life instance from the singleton
    }

    private void populate() {
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                gridPane.add(new LetterPane(j), j, i);
            }
        }
    }

    private FXMLLoader safelyChangeScreen(String fxmlPath) {
        return Helper.changeGameScreen(fxmlPath);
    }

    private void switchToEndScreen(boolean isFailure) {
        life.resetLives();
        FXMLLoader loader = safelyChangeScreen("wordle/WRLEndScreen.fxml");
        WRLEndController controller = loader.getController();
        if (isFailure) {
            controller.showFailureMessage();
        }
        controller.updateScore("Latest Score: " + Integer.toString(life.getLives() * 10));
    }

    @FXML public void handle(KeyEvent e) {
        warningLabel.setVisible(false);
        // Check If Game Over
        if (life.getLives() <= 0) {
            switchToEndScreen(true);
            return;
        }
        // Check If All Cells Are Filled
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
                if (currWord.compare(targetWord)) {
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
}
