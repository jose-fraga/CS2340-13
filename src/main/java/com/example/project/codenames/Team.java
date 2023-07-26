package com.example.project.codenames;

import com.example.project.codenames.enums.Player;
import com.example.project.codenames.enums.TeamType;
import com.example.project.codenames.enums.Type;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Team {
    private final PropertyChangeSupport support;
    private final Type type;

    private int numOfCards, score;
    private Player currentPlayer;
    private TeamType teamType;

    public Team(Type type, TeamType teamType) {
        this.support = new PropertyChangeSupport(this);
        this.type = type;
        this.teamType = teamType;
        this.numOfCards = teamType.getCardCount();
        this.currentPlayer = Player.SPY_MASTER;
    }

    public Type getType() { return this.type; }
    public int getNumOfCards() { return this.numOfCards; }

    public Player getCurrentPlayer() { return this.currentPlayer; }
    public void setCurrentPlayer(Player player) { this.currentPlayer = player; }

    public int getScore() { return this.score; }
    public void setScore(int value) {this.score = value; }

    public boolean isActiveTeam() { return this.teamType == TeamType.ACTIVE; }

    public void swapTeamType(boolean isReset) {
        this.teamType = (isActiveTeam()) ? TeamType.PASSIVE : TeamType.ACTIVE;
        if (isReset) {
            this.numOfCards = this.teamType.getCardCount();
        }
    }

    public void decrementCardCount() {
        this.support.firePropertyChange("numOfCards", this.numOfCards, --this.numOfCards);
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
        return "Team{" +
                "type=" + this.type + ", " +
                "numOfCards=" + this.numOfCards + ", " +
                "currentPlayer=" + this.currentPlayer + ", " +
                "teamType=" + this.teamType +
                '}';
    }
}
