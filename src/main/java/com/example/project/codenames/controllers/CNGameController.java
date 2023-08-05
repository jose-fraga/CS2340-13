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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

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
    @FXML private ListView<TextFlow> gameLog;

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
        round.addPropertyChangeListener(this);
        round.activeTeam().addPropertyChangeListener(this);
        round.passiveTeam().addPropertyChangeListener(this);
    }

    private void unregisterListeners() {
        round.removePropertyChangeListener(this);
        round.activeTeam().removePropertyChangeListener(this);
        round.passiveTeam().removePropertyChangeListener(this);
    }

    private void populateGrid() {
        scoreLabels.put(Type.RED, redScore);
        scoreLabels.put(Type.BLUE, blueScore);
        int count = 0;
        for (int i = 0; i < gridPane.getRowCount(); i++) {
            for (int j = 0; j < gridPane.getColumnCount(); j++) {
                gridPane.add(new WordPane(round.getWords().get(count)), j, i);
                count++;
            }
        }
        scoreLabels.get(round.activeTeam().getType()).setText(String.valueOf(round.activeTeam().getNumOfCards()));
        scoreLabels.get(round.passiveTeam().getType()).setText(String.valueOf(round.passiveTeam().getNumOfCards()));
        gameLog.setFocusTraversable(false);
        gameLog.getItems().addAll(round.getCurrentLog().getLogItems());
    }

    private void handle() {
        gridPane.getChildren().forEach(item -> {
            if (!(item instanceof Group)) {
                WordPane currPane = (WordPane) item;
                VBox currBox = (VBox) currPane.getChildren().get(0);
                if (round.activeTeam().getCurrentPlayer() == Player.OPERATIVE) {
                    if (currPane.getWord().getIsSelected()) {
                        currPane.addBackground();
                    } else {
                        currPane.addButton();
                        currBox.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                            currPane.handleGuess();
                        });
                        ((Button) currBox.getChildren().get(1)).setOnAction(e -> {
                            round.getCurrentLog().addLogItem(currPane.getWord(), round.activeTeam().getType().getColor());
                            currPane.getWord().select();
                            if (currPane.getWord().getIsSelected()) {
                                currPane.selectedUpdate();
                            }
                            gameLog.getItems().clear();
                            gameLog.getItems().addAll(round.getCurrentLog().getLogItems());
                        });
                    }
                } else {
                    currPane.addBackground();
                }
            }
        });
    }

    private void addTop() {
        Team currTeam = round.activeTeam();
        teamDisplay.setText(currTeam.getType().toString());
        teamDisplay.setStyle("-fx-text-fill: " + currTeam.getType().getColor());
        playerDisplay.setText(currTeam.getCurrentPlayer().getName());
        playerDisplay.setStyle("-fx-text-fill: blueviolet;");
        topTitle.setText(currTeam.getCurrentPlayer().getHint());
    }

    private void addBottom() {
        try {
            Player currPlayer = round.activeTeam().getCurrentPlayer();
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(currPlayer.getPath()));
            borderPane.setBottom(loader.load());
        } catch (Exception e) {
            e.printStackTrace(System.out);
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
            scoreLabels.get(updatedTeam.getType()).setText(String.valueOf(updatedTeam.getNumOfCards()));
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
