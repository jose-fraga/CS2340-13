package com.example.project.codenames;

import com.example.project.codenames.enums.Player;
import com.example.project.codenames.enums.Role;
import com.example.project.codenames.enums.Type;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Team {
    private final PropertyChangeSupport support;
    private final Type type;
    private int numOfCards, score;
    private Player currentPlayer;
    private Role roleType;

    public Team(Type type, Role roleType) {
        this.support = new PropertyChangeSupport(this);
        this.type = type;
        this.roleType = roleType;
        this.numOfCards = roleType.getCardCount();
        this.currentPlayer = Player.SPY_MASTER;
    }

    public Type getType() { return this.type; }
    public int getNumOfCards() { return this.numOfCards; }
    public Role getRoleType() { return this.roleType; }

    public Player getCurrentPlayer() { return this.currentPlayer; }
    public void setCurrentPlayer(Player player) { this.currentPlayer = player; }

    public int getScore() { return this.score; }
    public void setScore(int value) {this.score = value; }

    public boolean isActiveTeam() { return this.roleType == Role.ACTIVE; }

    public void swapRoleType(boolean isReset) {
        this.roleType = (isActiveTeam()) ? Role.PASSIVE : Role.ACTIVE;
        if (isReset) {
            this.numOfCards = this.roleType.getCardCount();
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
}
