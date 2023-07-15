package com.example.project.codenames.enums;

public enum CardType {
    RED("#CF2129"),
    BLUE("#03B8D0"),
    NEUTRAL("#F5D9B6"),
    ASSASSIN("#373737");

    private final String color;

    CardType(String color) { this.color = color; }

    public String getColor() { return this.color; }
}
