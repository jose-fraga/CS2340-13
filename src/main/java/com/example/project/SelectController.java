package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SelectController {
    public void flowSwitch(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "FlowScreen.fxml", "CS2340 - Flow");
    }

    public void twentySwitch(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "2048Screen.fxml", "CS2340 - 2048");
    }

    public void codeNameSwitch(ActionEvent actionEvent) {
    }
}
