package com.example.project.codenames;

import com.example.project.codenames.enums.Player;
import com.example.project.codenames.enums.Type;

public class Team {
    private final Type type;
    private int numOfCards;
    private Player currentPlayer;

    public Team(Type type, int count) {
        this.type = type;
        this.numOfCards = count;
        this.currentPlayer = Player.SPY_MASTER;
    }

    public Type getType() { return this.type; }

    public int getNumOfCards() { return this.numOfCards; }

    public Player getCurrentPlayer() { return this.currentPlayer; }
    public void setCurrentPlayer(Player player) { this.currentPlayer = player; }

    public void decrementCardCount() { this.numOfCards--; }
    public boolean hasWon() { return this.numOfCards <= 0; }

    @Override
    public String toString() {
        return "Team={type:}" + type + ", numOfCards:" + numOfCards + ", currentPlayer:" + currentPlayer + "}";
    }
}
