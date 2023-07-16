package com.example.project.codenames.controllers;

import com.example.project.codenames.CNGame;
import com.example.project.codenames.Round;
import com.example.project.codenames.WordPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CNGameScreenController implements Initializable {
    @FXML private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: Codenames Game Screen");
        populate();
        handle();
    }

    public void populate() {
        CNGame.getGameInstance().createRound();
        Round round = CNGame.getGameInstance().getRound();
        int count = 0;
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                gridPane.add(new WordPane(round.getWords().get(count)), j, i);
                count++;
            }
        }

    }

    public void handle() {
        gridPane.getChildren().forEach(item -> {
            if (!(item instanceof Group)) {
                WordPane curr = (WordPane) item;
                VBox currBox = (VBox) curr.getChildren().get(0);
                currBox.getChildren().get(1).addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
//                    curr.getWord().changeSelected();
                    curr.addBackground();
                });
            }
        });
    }
}
