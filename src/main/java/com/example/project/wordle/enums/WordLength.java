package com.example.project.wordle.enums;

public enum WordLength {
    FIVE("wordle/levels/Five.fxml");

    private String path;

    WordLength(String path) {
        this.path = path;
    }
}
