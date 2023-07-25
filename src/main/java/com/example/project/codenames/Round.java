package com.example.project.codenames;

import com.example.project.codenames.enums.Player;
import com.example.project.codenames.enums.Type;
import javafx.scene.control.Label;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// TODO: Make endGame an Observable

// Source: https://www.youtube.com/watch?v=icf5S9fzRXE
// We followed this tutorial to understand how to implement the Observer Pattern
// through using the PropertyChangeListener and PropertyChangeSupport. This
// code successfully adds listeners (observers), assigns support (observable),
// and notifies listeners when some property changes.
public class Round implements PropertyChangeListener {
    public static final String activeTeamEvent = "activeTeam";
    public static final String winnerEvent = "winner";

    private Team team1, team2, activeTeam;
    private ArrayList<Word> words;
    private ArrayList<Label> gameLogEvents = new ArrayList<>();

    private String currentClue;
    private int currentGuessLimit;
    private int currentGuessCount;

    private PropertyChangeSupport support;

    public Team getTeam1() { return team1; }
    public Team getTeam2() { return team2; }

    public Round() {
        this.support = new PropertyChangeSupport(this);
        this.team1 = this.activeTeam = new Random().nextBoolean() ? new Team(Type.RED, 9) : new Team(Type.BLUE, 9);
        this.team2 = (activeTeam.getType() == Type.RED) ? new Team(Type.BLUE, 8) : new Team(Type.RED, 8);
        updateWordType();
    }

    public String getCurrentClue() { return this.currentClue; }
    public int getCurrentGuessLimit() { return this.currentGuessLimit; }

    public ArrayList<Word> getWords() { return this.words; }

    public Team getActiveTeam() {
        return this.activeTeam;
    }

    public ArrayList<Label> getGameLogEvents() { return this.gameLogEvents; }

    public boolean isTeamActive(Type team) {
        return this.activeTeam.getType() == team;
    }

    private void updateWordType() {
        DictionaryService.populate();
        this.words = DictionaryService.getGameWords();

        Collections.shuffle(this.words);

        addType(0, 9, team1.getType());
        addType(9, 17, team2.getType());

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
        Team passiveTeam = (this.activeTeam == this.team1) ? this.team2 : this.team1;

        Label logEvent = new Label();
        logEvent.setStyle("-fx-border-color: " + this.activeTeam.getType().toString().toLowerCase());

//        gameLogEvents.add(new Label(selected.getWord()));

        // selected = Assassin (Incorrect -> endGame)
        if (selected.getType() == Type.ASSASSIN) {
            System.out.println("SELECTED: Assassin Card");
            logEvent.setText("SELECTED: Assassin Card");

            this.activeTeam = passiveTeam;
            endGame(passiveTeam);

        // selected = Other Team (Incorrect -> endTurn)
        } else if (selected.getType() == passiveTeam.getType()) {
            System.out.println("SELECTED: Enemy Card");
            logEvent.setText("SELECTED: Enemy Card");
            passiveTeam.decrementCardCount();
            if (passiveTeam.hasWon()) {
                endGame(passiveTeam);
            } else {
                endTurn();
            }

        // selected = Neutral (Incorrect -> endTurn)
        } else if (selected.getType() == Type.NEUTRAL) {
            System.out.println("SELECTED: Neutral Card");
            logEvent.setText("SELECTED: Neutral Card");
            endTurn();

        // selected = Your Team (Correct -> endTurn or endGame)
        } else if (selected.getType() == activeTeam.getType()) {
            System.out.println("SELECTED: Team Card");
            logEvent.setText("SELECTED: Team Card");
            this.activeTeam.decrementCardCount();
            this.currentGuessCount++;

            if (this.activeTeam.hasWon()) {
                endGame(this.activeTeam);
            } else if (this.currentGuessCount == this.currentGuessLimit) {
                endTurn();
            }
        }

        gameLogEvents.add(logEvent);
    }

    public void setClue(String clue, int clueCount) {
        if (this.activeTeam.getCurrentPlayer() == Player.SPY_MASTER) {
            this.activeTeam.setCurrentPlayer(Player.OPERATIVE);
            this.currentGuessCount = 0;
            this.currentGuessLimit = clueCount + 1;
            this.currentClue = clue;
        }
    }

    public void endTurn() {
        Team previousTeam = this.activeTeam;
        this.activeTeam = (this.activeTeam == this.team1) ? this.team2 : this.team1;
        this.activeTeam.setCurrentPlayer(Player.SPY_MASTER);
        this.currentClue = "";
        this.support.firePropertyChange(activeTeamEvent, previousTeam , activeTeam);
    }

    private void endGame(Team winner) {
        this.support.firePropertyChange(winnerEvent, null, winner);
        System.out.println("Game ends! " + winner);
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
