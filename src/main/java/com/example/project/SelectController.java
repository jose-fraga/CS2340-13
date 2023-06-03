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

public class SelectController implements Initializable {
    @FXML
    private Label myLabel;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    private String[] characterSelection = {"Character 1","Character 2","Character 3", "Character 4"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myChoiceBox.getItems().addAll(characterSelection);
        myChoiceBox.setOnAction(this::getCharacterSelection);
    }
    public void getCharacterSelection(ActionEvent event) {
        String mySelection = myChoiceBox.getValue();
        myLabel.setText(mySelection);
    }

    public void flowSwitch(ActionEvent actionEvent) {
          Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
          Helper.changeScreen(stage, "FlowScreen.fxml", "CS2340 Project");
    }

    public void twentySwitch(ActionEvent actionEvent) {
    }

    public void codeNameSwitch(ActionEvent actionEvent) {
    }
}
