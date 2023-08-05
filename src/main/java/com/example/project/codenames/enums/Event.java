package com.example.project.codenames.enums;

public enum Event {
    UPDATE_COUNT("numOfCards"),
    SWITCH_TEAM("endTeamTurn"),
    SWITCH_PLAYER("endPlayerTurn"),
    GAME_OVER("endGame");

    private final String propertyName;

    Event(String name) {
        this.propertyName = name;
    }

    public String getPropertyName() { return this.propertyName; }
}
