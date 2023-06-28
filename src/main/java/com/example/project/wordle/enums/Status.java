package com.example.project.wordle.enums;

import javafx.scene.paint.Color;

public enum Status {
    EMPTY("#FFFFFF"),
    INCORRECT("#3A3A3C"),
    PARTIAL("#B59F3B"),
    CORRECT("#538D4E");

    private String color;

    Status(String color) {
        this.color = color;
    }

    public String getColor() { return this.color; }
}
