package com.example.project.codenames.enums;

public enum Team {
    BLUE("#3385a4"),
    RED("#902b1c"),
    NEUTRAL("#f5d9b6"),
    ASSASSIN("#3c3c3c");

    private String color;

    Team(String color) { this.color = color; }

    public String getColor() { return this.color; }
}
