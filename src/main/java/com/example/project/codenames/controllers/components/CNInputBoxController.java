package com.example.project.codenames.controllers.components;

import com.example.project.Helper;
import com.example.project.codenames.CNGame;
import com.example.project.codenames.GameLog;
import com.example.project.codenames.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Source: https://www.youtube.com/watch?v=icf5S9fzRXE
// We followed this tutorial to understand how to implement the Observer Pattern
// through using the PropertyChangeListener and PropertyChangeSupport. This
// code successfully adds listeners (observers), assigns support (observable),
// and notifies listeners when some property changes.
public class CNInputBoxController implements Initializable {
    @FXML private TextField clueInput;
    @FXML private ChoiceBox<Integer> guessInput;
    @FXML private Text warningText;

    private PropertyChangeSupport support;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.support = new PropertyChangeSupport(this);
        warningText.setVisible(false);
        guessInput.getItems().addAll(IntStream.range(1,9).boxed().collect(Collectors.toList()));
        this.addPropertyChangeListener(CNGame.getGameInstance().getRound().getCurrentLog());
    }

    @FXML public void switchToOperative(ActionEvent e) {
        if (clueInput.getText().isBlank() || guessInput.getValue() == null) {
            warningText.setVisible(true);
            System.out.println("TRY AGAIN!");
        } else {
            String clue = clueInput.getText();
            int clueCount = guessInput.getValue();

            System.out.println("Clue: " + clue + " " + clueCount);

            this.support.firePropertyChange("clue", null, CNGame.getGameInstance().getRound().getActiveTeam().getType().getColor());
            CNGame.getGameInstance().getRound().setClue(clue, clueCount);
            Helper.changeGameScreen("codenames/CNBufferScreen.fxml");
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }
}
