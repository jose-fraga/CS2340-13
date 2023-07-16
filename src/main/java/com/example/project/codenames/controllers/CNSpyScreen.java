package com.example.project.codenames.controllers;

import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class CNSpyScreen {
    private CNController cnController;

    public void setCNController(CNController controller) {
        this.cnController = controller;
    }

    @FXML
    private TextField clueTextField;

    @FXML
    private TextField guessCountTextField;

    @FXML
    private void submitClue(ActionEvent e) {
        String clue = clueTextField.getText();
        int guessCount = Integer.parseInt(guessCountTextField.getText());

        if (cnController != null) {
            cnController.processClue(clue, guessCount);
        }

        clueTextField.clear();
        guessCountTextField.clear();
    }

    @FXML
    private void endTurn(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "codenames/CNBufferScreen.fxml", "CS2340 - " + Helper.currentGame.title() + " (Splash)");
    }
}
