package com.example.project.codenames.controllers;

import com.example.project.Helper;
import com.example.project.Main;
import com.example.project.codenames.CNGame;
import com.example.project.codenames.Round;
import com.example.project.codenames.Team;
import com.example.project.codenames.WordPane;
import com.example.project.codenames.enums.Event;
import com.example.project.codenames.enums.Player;
import com.example.project.codenames.enums.Type;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
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

// Source: https://www.youtube.com/watch?v=icf5S9fzRXE
// We followed this tutorial to understand how to implement the Observer Pattern
// through using the PropertyChangeListener and PropertyChangeSupport. This
// code successfully adds listeners (observers), assigns support (observable),
// and notifies listeners when some property changes.
public class CNGameController implements Initializable, PropertyChangeListener {
    @FXML private BorderPane borderPane;
    @FXML private GridPane gridPane;
    @FXML private Label teamDisplay, playerDisplay, topTitle, redScore, blueScore;

    private final Round round = CNGame.getGameInstance().getRound();
    private final HashMap<Type, Label> scoreLabels = new HashMap<>(2);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerListeners();
        populateGrid();
        handle();
        addTop();
        addBottom();
    }

    private void registerListeners() {
        this.round.addPropertyChangeListener(this);
        this.round.activeTeam().addPropertyChangeListener(this);
        this.round.passiveTeam().addPropertyChangeListener(this);
    }

    private void unregisterListeners() {
        this.round.removePropertyChangeListener(this);
        this.round.activeTeam().removePropertyChangeListener(this);
        this.round.passiveTeam().removePropertyChangeListener(this);
    }

    private void populateGrid() {
        this.scoreLabels.put(Type.RED, redScore);
        this.scoreLabels.put(Type.BLUE, blueScore);
        int count = 0;
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                gridPane.add(new WordPane(this.round.getWords().get(count)), j, i);
                count++;
            }
        }
        this.scoreLabels.get(this.round.activeTeam().getType()).setText(
                String.valueOf(this.round.activeTeam().getNumOfCards())
        );
        this.scoreLabels.get(this.round.passiveTeam().getType()).setText(
                String.valueOf(this.round.passiveTeam().getNumOfCards())
        );
    }

    private void handle() {
        gridPane.getChildren().forEach(item -> {
            if (!(item instanceof Group)) {
                WordPane currPane = (WordPane) item;
                VBox currBox = (VBox) currPane.getChildren().get(0);
                if (this.round.activeTeam().getCurrentPlayer() == Player.OPERATIVE) {
                    if (currPane.getWord().getIsSelected()) {
                        currPane.addBackground();
                    } else {
                        currPane.addButton();
                        currBox.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                            currPane.handleGuess();
                        });
                        ((Button) currBox.getChildren().get(1)).setOnAction(e -> {
                            currPane.getWord().select();
                            if (currPane.getWord().getIsSelected()) {
                                currPane.selectedUpdate();
                            }
                        });
                    }
                } else {
                    currPane.addBackground();
                }
            }
        });
    }

    private void addTop() {
        teamDisplay.setText(this.round.activeTeam().getType().toString());
        teamDisplay.setStyle("-fx-text-fill: " + this.round.activeTeam().getType().getColor());
        playerDisplay.setText(this.round.activeTeam().getCurrentPlayer().toString());
        topTitle.setText(this.round.activeTeam().getCurrentPlayer().getHint());
    }

    private void addBottom() {
        Player currPlayer = this.round.activeTeam().getCurrentPlayer();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(currPlayer.getPath()));
            this.borderPane.setBottom(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private FXMLLoader safelyChangeScreen(String fxmlPath) {
        return Helper.changeGameScreen(fxmlPath);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String currEvt = evt.getPropertyName();
        if (currEvt.equals(Event.UPDATE_COUNT.getPropertyName())) {
            Team updatedTeam = (Team) evt.getSource();
            this.scoreLabels.get(updatedTeam.getType()).setText(String.valueOf(updatedTeam.getNumOfCards()));
        } else if (currEvt.equals(Event.GAME_OVER.getPropertyName())) {
            unregisterListeners();
            CNGame.getGameInstance().clearRound();
            FXMLLoader loader = safelyChangeScreen("codenames/CNEndScreen.fxml");
            CNEndController controller = loader.getController();
            Team winningTeam = (Team) evt.getNewValue();
            controller.updateScreen(winningTeam.getType(), winningTeam.getScore());
        } else {
            FXMLLoader loader = safelyChangeScreen("codenames/CNBufferScreen.fxml");
            CNBufferController controller = loader.getController();
            if (currEvt.equals(Event.SWITCH_PLAYER.getPropertyName())) {
                controller.setBufferLabel("Pass Device To " + evt.getNewValue() + "'s Operatives!");
            } else if (currEvt.equals(Event.SWITCH_TEAM.getPropertyName())) {
                unregisterListeners();
                controller.setBufferLabel("Pass Device To " + evt.getNewValue() + "'s Spymaster!");
            }
        }
    }
}
