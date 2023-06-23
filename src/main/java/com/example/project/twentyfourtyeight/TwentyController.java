package com.example.project.twentyfourtyeight;

import com.example.project.Game;
import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TwentyController implements Initializable {
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Reserved for initializing variables, etc.
    }

    @FXML
    private void returnToSelect(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "SelectScreen.fxml", "CS2340 Project");
    }

    @FXML
    private void continueToConfigure(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.TWENTY_FOURTY_EIGHT.configFxmlPath(), "CS2340 Configuration");
    }
}

