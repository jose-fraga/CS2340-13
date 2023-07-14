package com.example.project.codenames.controllers;

import com.example.project.codenames.CNGame;
import com.example.project.codenames.DictionaryService;
import com.example.project.codenames.Round;
import com.example.project.codenames.WordPane;
import com.example.project.codenames.enums.Team;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CNGridScreenController implements Initializable {
    @FXML private GridPane gridPane;
  //  @FXML private CheckBox checkBox;

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

        gridPane.getChildren().forEach(item-> {
            if (!(item instanceof Group)) {
                WordPane curr = (WordPane) item;
                VBox currBox = (VBox) curr.getChildren().get(0);
                currBox.getChildren().get(1).addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                    curr.addBackground();
                });

                currBox.addEventFilter(MouseEvent.MOUSE_CLICKED, e-> {
                            Round round = CNGame.getGameInstance().getRound();
                            Label text = new Label("Red");

                    if (round.getCurrentTeamTurn() == Team.RED && curr.getOccupant() != Team.RED) {
                        currBox.getChildren().add(text);
                        text.setStyle("-fx-background-color:" + Team.RED.getColor());
                        curr.SetOccupant(Team.RED);
                        System.out.println(curr.getOccupant());
                    }
                    else if (round.getCurrentTeamTurn() == Team.RED && curr.getOccupant() == Team.RED){
                        currBox.getChildren().remove(2);
                        curr.SetOccupant(Team.NEUTRAL);
                    }
                    else if (round.getCurrentTeamTurn() == Team.BLUE && curr.getOccupant() != Team.BLUE) {
                        currBox.getChildren().add(text);
                        text.setStyle("-fx-background-color:" + Team.BLUE.getColor());
                        curr.SetOccupant(Team.BLUE);
                        System.out.println(curr.getOccupant());
                    }
                    else if (round.getCurrentTeamTurn() == Team.BLUE && curr.getOccupant() == Team.BLUE){
                        currBox.getChildren().remove(2);
                        curr.SetOccupant(Team.NEUTRAL);
                    }
                });
            }
        });
    }
}
