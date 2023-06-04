package com.example.project;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigureController implements Initializable {
    @FXML
    private ChoiceBox<String> characterSelect;
    private String[] characters = {"Character #1","Character #2","Character #3", "Character #4"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        characterSelect.getItems().addAll(characters);
    }

    @FXML
    private void continueToGame(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        String screenName;
        switch (Helper.currentGame) {
            case "Flow":
                Helper.changeScreen(stage, "FlowGameScreen.fxml", "CS2340 - Flow (Game)");
                break;
            case "2048":
                Helper.changeScreen(stage, "2048GameScreen.fxml", "CS2340 - 2048 (Game)");
                break;
            case "Codenames":
                // Set this to Codename Game Screen
                break;
        }
    }
}
