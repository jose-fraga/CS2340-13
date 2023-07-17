package com.example.project.codenames.controllers;

import com.example.project.codenames.CNGame;
import com.example.project.codenames.DictionaryService;
import com.example.project.codenames.Round;
import com.example.project.codenames.WordPane;
import com.example.project.codenames.enums.Type;
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
                    Label text = new Label(String.valueOf(round.getActiveTeam().getType()));
                    int curl = curr.getCurrLength();

                    if (round.isTeamActive(Type.RED) && !curr.hasOccupant(Type.RED)) {
                       curl++;
                       curr.setRedPosition(getPosition(curl));
                       curr.setCurrLength(curl);
                        currBox.getChildren().add(text);
                        text.setStyle("-fx-background-color:" + Type.RED.getColor());
                        curr.ToggleOccupants(Type.RED);
                        System.out.println(curr.getOccupants());
                    }
                    else if (round.isTeamActive(Type.RED) && curr.hasOccupant(Type.RED)){
                        curl--;
                        curr.ToggleOccupants(Type.RED);
                        curr.setRedPosition(getPosition(curl));
                        removal(currBox, curr.getRedPosition(), curr.getBluePosition(), round.getActiveTeam().getType());
                        curr.setCurrLength(curl);
                        System.out.println(curr.getOccupants());

                    }
                    else if (round.isTeamActive(Type.BLUE) && !curr.hasOccupant(Type.BLUE)) {
                        curl++;
                        curr.setBluePosition(getPosition(curl));
                        curr.setCurrLength(curl);
                        currBox.getChildren().add(text);
                        text.setStyle("-fx-background-color:" + Type.BLUE.getColor());
                        curr.ToggleOccupants(Type.BLUE);
                        System.out.println(curr.getOccupants());
                    }
                    else if (round.isTeamActive(Type.BLUE) && curr.hasOccupant(Type.BLUE)){
                        curl--;
                        curr.ToggleOccupants(Type.BLUE);
                        curr.setBluePosition(getPosition(curl));
                        removal(currBox, curr.getRedPosition(), curr.getBluePosition(), round.getActiveTeam().getType());
                        curr.setCurrLength(curl);
                        System.out.println(curr.getOccupants());
                    }
                });
            }
        });
    }

    //method to return a label's new position on the wordpane after it is added or removed
   public int getPosition(int currLength) {
        int position = 0;
        if(currLength == 1) {
            position = 2;
        } else if (currLength == 0) {
            position = 1;
        }
        return position;
    }

    //method which takes in the position of the label being removed and removes it accordingly
    public void removal (VBox currBox, int redPos, int bluePos, Type team) {
        if(team == Type.RED) {
            if(redPos == 1) {
                currBox.getChildren().remove(2);
            } else if (redPos == 2) {
                currBox.getChildren().remove(3);
            }
        }
        else if(team == Type.BLUE) {
            if(bluePos == 1) {
                currBox.getChildren().remove(2);
            } else if (bluePos == 2) {
                currBox.getChildren().remove(3);
            }
        }
        }
    }
