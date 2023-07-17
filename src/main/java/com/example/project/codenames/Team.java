package com.example.project.codenames;

import com.example.project.codenames.enums.Player;
import com.example.project.codenames.enums.Type;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Team {
    public static final String numOfCardsUpdatedEvent = "numOfCardsUpdatedEvent";
    private PropertyChangeSupport support;
    private final Type type;
    private int numOfCards;
    private Player currentPlayer;

    public Team(Type type, int count) {
        this.support = new PropertyChangeSupport(this);
        this.type = type;
        this.numOfCards = count;
        this.currentPlayer = Player.SPY_MASTER;
    }

    public Type getType() { return this.type; }

    public int getNumOfCards() { return this.numOfCards; }

    public Player getCurrentPlayer() { return this.currentPlayer; }
    public void setCurrentPlayer(Player player) { this.currentPlayer = player; }

    public void decrementCardCount() {
        int old = this.numOfCards;
        this.numOfCards--;
        this.support.firePropertyChange(numOfCardsUpdatedEvent, old, this.numOfCards);
    }
    public boolean hasWon() { return this.numOfCards <= 0; }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.support.removePropertyChangeListener(listener);
    }

    @Override
    public String toString() {
        return "Team={type:" + type + ", numOfCards:" + numOfCards + ", currentPlayer:" + currentPlayer + "}";
    }
}
