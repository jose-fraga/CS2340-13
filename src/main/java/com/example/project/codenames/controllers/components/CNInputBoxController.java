package com.example.project.codenames.controllers.components;

import com.example.project.Helper;
import com.example.project.codenames.CNGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CNInputBoxController implements Initializable {
    @FXML private TextField clueInput;
    @FXML private ChoiceBox<Integer> guessInput;
    @FXML private Text warningText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        warningText.setVisible(false);
        guessInput.getItems().addAll(IntStream.range(1,9).boxed().collect(Collectors.toList()));
    }

    @FXML public void switchToOperative(ActionEvent e) {
        if (clueInput.getText().isBlank() || guessInput.getValue() == null) {
            warningText.setVisible(true);
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
