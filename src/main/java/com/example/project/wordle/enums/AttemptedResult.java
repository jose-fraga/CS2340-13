package com.example.project.wordle.enums;

import com.example.project.flowfree.GridItem;

public enum AttemptedResult {
    UNKNOWN("#121213"),
    INCORRECT("#3A3A3C"),
    PARTIAL("#B59F3B"),
    CORRECT("#538D4E");

    private String color;
    
    AttemptedResult(String color) {
        this.color = color;
    }
    public String color() { return this.color; }
}
