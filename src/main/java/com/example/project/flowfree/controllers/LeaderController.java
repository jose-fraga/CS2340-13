package com.example.project.flowfree.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LeaderController implements Initializable {
    @FXML private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gridPane.add(new Label("John Doe"), 0, 0);
        System.out.println("TESTING");
    }
}
