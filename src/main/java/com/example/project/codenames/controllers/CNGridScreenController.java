package com.example.project.codenames.controllers;

import com.example.project.codenames.DictionaryService;
import com.example.project.codenames.WordPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CNGridScreenController implements Initializable {
    @FXML private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: Codenames Grid");
        DictionaryService.populate();
        populate();
        handle();
    }

    public void populate() {
        int count = 0;
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                gridPane.add(new WordPane(DictionaryService.getGameWords().get(count)), j, i);
                count++;
            }
        }
    }

    public void handle() {
        gridPane.getChildren().forEach(item -> {
            item.addEventFilter(MouseEvent.MOUSE_CLICKED, System.out::println);
        });
    }
}
