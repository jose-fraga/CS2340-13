package com.example.project.codenames.enums;

public enum Role {
    ACTIVE(9),
    PASSIVE(8);

    Role(int count) {
        this.cardCount = count;
    }

    private final int cardCount;

    public int getCardCount() { return this.cardCount; }
}
