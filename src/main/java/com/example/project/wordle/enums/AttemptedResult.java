package com.example.project.wordle.enums;

import com.example.project.flowfree.GridItem;

public enum AttemptedResult {
    UNKNOWN("#000000"),
    INCORRECT("#000000"),
    PARTIAL("#000000"),
    CORRECT("#000000");

    private String color;
    
    AttemptedResult(String color) {
        this.color = color;
    }
    public String color() { return this.color; }
}
