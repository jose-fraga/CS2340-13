package com.example.project.codenames;

import com.example.project.codenames.enums.Player;
import com.example.project.codenames.enums.Type;

public class Team {
    private Type type;
    private int numOfCards;
    private Player player;

    public Team(Type type, int count) {
        this.type = type;
        this.numOfCards = count;
        this.player = Player.SPY_MASTER;
    }

    public Type getType() { return this.type; }
    public Player getPlayer() { return this.player; }
}
