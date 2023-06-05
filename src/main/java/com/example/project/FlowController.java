package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FlowController implements Initializable {
    @FXML
    private Button btn2;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
            System.out.println("Flow Free Main Start Screen");
            // required code to start the game: initializing variables, etc
    }

    public void returnButton(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "SelectScreen.fxml", "CS2340 Project");
    }

    public void startFlowFreeButton(ActionEvent actionEvent) {

    }

    public void playFlowFree(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "ConfigureScreen.fxml", "CS2340 " + Helper.currentGame.title() + " Configuration");
    }
}

