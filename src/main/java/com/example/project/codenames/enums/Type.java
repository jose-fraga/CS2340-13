package com.example.project.codenames.enums;

public enum Type {
    RED("#CF2129"),
    BLUE("#03B8D0"),
    NEUTRAL("#E6CCA2"),
    ASSASSIN("#373737");

    private final String color;

    Type(String color) { this.color = color; }

    public String getColor() { return this.color; }
}
