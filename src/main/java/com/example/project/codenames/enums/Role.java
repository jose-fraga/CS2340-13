package com.example.project.codenames.enums;

public enum TeamType {
    ACTIVE(9),
    PASSIVE(8);

    TeamType(int count) {
        this.cardCount = count;
    }

    private final int cardCount;

    public int getCardCount() { return this.cardCount; }
}
