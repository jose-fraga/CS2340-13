package com.example.project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;

public class SelectController {
    @FXML
    private Button game1Button;
    @FXML
    private Button game2Button;
    @FXML
    private Button game3Button;

    @FXML
    private void initialize(){
        game1Button.setOnAction(event -> {
            // Code to start Flow
        });
        game2Button.setOnAction(event -> {
            // Code to start 2048
        });
        game3Button.setOnAction(event -> {
            // Code to start Codenames
        });
    }
}
