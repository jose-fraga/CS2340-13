package com.example.project.codenames.controllers;

import com.example.project.Helper;
import com.example.project.codenames.CNGame;
import com.example.project.codenames.Round;
import com.example.project.codenames.enums.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CNInputBoxController implements Initializable {
    @FXML private TextField clueInput;
    @FXML private ChoiceBox<Integer> guessInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guessInput.getItems().addAll(IntStream.range(0,9).boxed().collect(Collectors.toList()));
    }

    @FXML public void switchToOperative(ActionEvent e) {
        if (clueInput.getText().isBlank() || guessInput.getValue() == null) {
            System.out.println("TRY AGAIN!");
        } else {
            String clue = clueInput.getText();
            int clueCount = guessInput.getValue();
            System.out.println("Clue: " + clue + " " + clueCount);
            CNGame.getGameInstance().getRound().setClue(clue, clueCount);
            Helper.changeGameScreen("codenames/CNBufferScreen.fxml");
        }
    }
}
