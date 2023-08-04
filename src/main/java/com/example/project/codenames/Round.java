package com.example.project.codenames;

import com.example.project.codenames.enums.Player;
import com.example.project.codenames.enums.Role;
import com.example.project.codenames.enums.Type;
import javafx.scene.control.Label;
import javafx.scene.text.TextFlow;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Source: https://www.youtube.com/watch?v=icf5S9fzRXE
// We followed this tutorial to understand how to implement the Observer Pattern
// through using the PropertyChangeListener and PropertyChangeSupport. This
// code successfully adds listeners (observers), assigns support (observable),
// and notifies listeners when some property changes.
public class Round implements PropertyChangeListener {
    private final PropertyChangeSupport support;
    private Team redTeam, blueTeam;
    private ArrayList<Word> words;
    private String currentClue;
    private int currGuessLimit, currGuessCount;

//    public GameLog getCurrentLog() { return this.currentLog; }

    public Round() {
        this.support = new PropertyChangeSupport(this);
        populateTeams();
        updateWordType();
    }

    public ArrayList<Word> getWords() { return this.words; }
    public String getCurrentClue() { return this.currentClue; }
    public int getCurrGuessLimit() { return this.currGuessLimit; }

    public Team activeTeam() { return (this.redTeam.isActiveTeam()) ? this.redTeam : this.blueTeam; }
    public Team passiveTeam() { return (this.redTeam.isActiveTeam()) ? this.blueTeam : this.redTeam; }

    public void swapTeams(boolean isReset) {
        this.redTeam.swapRoleType(isReset);
        this.redTeam.swapRoleType(isReset);
    }

    private void populateTeams() {
        this.redTeam = new Team(Type.RED, (new Random().nextBoolean()) ? Role.ACTIVE : Role.PASSIVE);
        this.blueTeam = new Team(Type.BLUE, (this.redTeam.isActiveTeam()) ? Role.PASSIVE : Role.ACTIVE);
    }

    private void updateWordType() {
        DictionaryService.populate();
        this.words = DictionaryService.getGameWords();
        Collections.shuffle(this.words);
        addType(0, 9, activeTeam().getType());
        addType(9, 17, passiveTeam().getType());
        Word assassinWord = this.words.get(24);
        assassinWord.setType(Type.ASSASSIN);
        Collections.shuffle(this.words);
        this.words.forEach(item -> item.addPropertyChangeListener(this));
    }

    private void addType(int start, int end, Type type) {
        for (int i = start; i < end; i++) {
            this.words.get(i).setType(type);
        }
    }

    public void checkSelectedWord(Word selected) {
        if (selected.getType() == Type.ASSASSIN) {
//            System.out.println("A.T. SELECTED: Assassin Card (Game Over)");
            passiveTeam().setScore(188);
            endGame(passiveTeam());
        } else if (selected.getType() == Type.NEUTRAL) {
//            System.out.println("A.T. SELECTED: Neutral Card (End Turn)");
            activeTeam().setScore(activeTeam().getScore() - 5);
            endTurn();
        } else if (selected.getType() == passiveTeam().getType()) {
//            System.out.println("A.T. SELECTED: Enemy Card (End Turn/Game Over)");
            passiveTeam().decrementCardCount();
            activeTeam().setScore(passiveTeam().getScore() + 20);
            if (passiveTeam().hasWon()) {
                endGame(passiveTeam());
            } else {
                endTurn();
            }
        } else if (selected.getType() == activeTeam().getType()) {
//            System.out.println("A.T. SELECTED: Team Card (Continue/End Turn/Game Over)");
            activeTeam().decrementCardCount();
            activeTeam().setScore(activeTeam().getScore() + 20);
            this.currGuessCount++;
            if (activeTeam().hasWon()) {
                endGame(activeTeam());
            } else if (this.currGuessCount == this.currGuessLimit) {
                endTurn();
            }
        }
    }

    public void setClue(String clue, int clueCount) {
        if (activeTeam().getCurrentPlayer() == Player.SPY_MASTER) {
            activeTeam().setCurrentPlayer(Player.OPERATIVE);
            this.currGuessCount = 0;
            this.currGuessLimit = ++clueCount;
            this.currentClue = clue;
            this.support.firePropertyChange("endPlayerTurn", null, activeTeam().getType());
        }
    }

    public void endTurn() {
        swapTeams(false);
        activeTeam().setCurrentPlayer(Player.SPY_MASTER);
        this.currentClue = "";
        this.support.firePropertyChange("endTeamTurn", null, activeTeam().getType());
    }

    private void endGame(Team winner) {
        this.support.firePropertyChange("endGame", null, winner);
        swapTeams(true);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        checkSelectedWord((Word) evt.getSource());
    }
}
