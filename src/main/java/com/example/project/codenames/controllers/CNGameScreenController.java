package com.example.project.codenames.controllers;

import com.example.project.Helper;
import com.example.project.Main;
import com.example.project.codenames.CNGame;
import com.example.project.codenames.Round;
import com.example.project.codenames.Team;
import com.example.project.codenames.WordPane;
import com.example.project.codenames.enums.Player;
import com.example.project.codenames.enums.Type;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

// TODO: Make enums for propertynames

// Source: https://www.youtube.com/watch?v=icf5S9fzRXE
// We followed this tutorial to understand how to implement the Observer Pattern
// through using the PropertyChangeListener and PropertyChangeSupport. This
// code successfully adds listeners (observers), assigns support (observable),
// and notifies listeners when some property changes.
public class CNGameScreenController implements Initializable, PropertyChangeListener {
    @FXML private BorderPane borderPane;
    @FXML private GridPane gridPane;
    @FXML private Label teamDisplay, playerDisplay, topTitle;
    @FXML private Label redTeamScore, blueTeamScore;

    private HashMap<Type, Label> scoreLabels = new HashMap<Type, Label>(2);
    private final Round round = CNGame.getGameInstance().getRound();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: Codenames Game Screen");

        scoreLabels.put(Type.RED, redTeamScore);
        scoreLabels.put(Type.BLUE, blueTeamScore);

        subscribeToEvents();
        populate();
        handle();

        addTop();
        addBottom();
    }

    private void subscribeToEvents() {
        this.round.addPropertyChangeListener(this);
        this.round.getTeam1().addPropertyChangeListener(this);
        this.round.getTeam2().addPropertyChangeListener(this);
    }

    private void unsubscribeFromEvents() {
        this.round.removePropertyChangeListener(this);
        this.round.getTeam1().removePropertyChangeListener(this);
        this.round.getTeam2().removePropertyChangeListener(this);
    }

    private void populate() {
        int count = 0;
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                gridPane.add(new WordPane(round.getWords().get(count)), j, i);
                count++;
            }
        }

        scoreLabels.get(this.round.getTeam1().getType()).setText(this.round.getTeam1().getNumOfCards() + "");
        scoreLabels.get(this.round.getTeam2().getType()).setText(this.round.getTeam2().getNumOfCards() + "");
    }

    private void handle() {
        gridPane.getChildren().forEach(item -> {
            if (!(item instanceof Group)) {
                WordPane curr = (WordPane) item;
                VBox currBox = (VBox) curr.getChildren().get(0);

                if (this.round.getActiveTeam().getCurrentPlayer() == Player.OPERATIVE) {
                    if (curr.getWord().getIsSelected()) {
                        curr.addBackground();
                    } else {
                        curr.addButton();
                        currBox.getChildren().get(1).addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                            curr.getWord().select();
                            if (curr.getWord().getIsSelected()) {
                                curr.selectedUpdate();
                            }
                        });
                    }
                } else {
                    curr.addBackground();
                }
            }
        });
    }

    private void addTop() {
        teamDisplay.setText(this.round.getActiveTeam().getType().toString());
        teamDisplay.setStyle("-fx-text-fill: " + this.round.getActiveTeam().getType().getColor());
        playerDisplay.setText(this.round.getActiveTeam().getCurrentPlayer().toString());
        topTitle.setText(this.round.getActiveTeam().getCurrentPlayer().getHint());
    }

    private void addBottom() {
        Player currPlayer = this.round.getActiveTeam().getCurrentPlayer();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(currPlayer.getPath()));
            Helper.setCNGamePane(borderPane);
            Helper.getCNGamePane().setBottom(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String incomingEvent = evt.getPropertyName();
        if (incomingEvent.equals(Round.activeTeamEvent)) {
            unsubscribeFromEvents();
            Helper.changeGameScreen("codenames/CNBufferScreen.fxml");
        } else if (incomingEvent.equals(Round.winnerEvent)) {
            Team winningTeam = (Team) evt.getNewValue();
            CNGame.getGameInstance().clearRound();
            unsubscribeFromEvents();
            Helper.changeGameScreen("codenames/CNEndScreen.fxml");
        } else if (incomingEvent.equals(Team.numOfCardsUpdatedEvent)) {
            // evt check blue or red type
            Team updatedTeam = (Team) evt.getSource();
            scoreLabels.get(updatedTeam.getType()).setText(updatedTeam.getNumOfCards() + "");
        }

    }
}
