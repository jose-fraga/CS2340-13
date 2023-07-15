package com.example.project.codenames.controllers;

import com.example.project.codenames.*;
import com.example.project.codenames.enums.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;

public class CNGameScreenController implements Initializable, PropertyChangeListener {
    @FXML private BorderPane borderPane;
    @FXML private GridPane gridPane;
    @FXML private HBox spyMasterBox;
    @FXML private TextField clueInput;

    private Round round;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: Codenames Game Screen");
        populate();
        handle();
    }

    public void populate() {
        CNGame.getGameInstance().createRound();
        CNGame.getGameInstance().getRound().addPropertyChangeListener(this);
        this.round = CNGame.getGameInstance().getRound();

        int count = 0;
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                boolean show = round.getActiveTeam().getCurrentPlayer() == Player.SPY_MASTER;
                gridPane.add(new WordPane(round.getWords().get(count), show), j, i);
                count++;
            }
        }
    }

    public void handle() {
        gridPane.getChildren().forEach(item -> {
            if (!(item instanceof Group)) {
                WordPane curr = (WordPane) item;
                VBox currBox = (VBox) curr.getChildren().get(0);

                if (this.round.getActiveTeam().getCurrentPlayer() == Player.OPERATIVE) {
                    currBox.getChildren().get(1).addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                        curr.getWord().select();
                        if (curr.getWord().getIsSelected()) {
                            curr.addBackground();
                        }
                    });
                } else {
                    curr.addBackground();
                }
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt);
        if (evt.getPropertyName().equals("activeTeam")) { //TODO make enums for propertynames
            Team activeTeam = (Team) evt.getNewValue();
            // switch screen to buffer screen so spy master can give clue
        }
    }

    @FXML public void switchToOperative() {
        System.out.println(clueInput.getText());
        this.round.getActiveTeam().setCurrentPlayer(Player.OPERATIVE);
        spyMasterBox.setVisible(false);
    }
}
