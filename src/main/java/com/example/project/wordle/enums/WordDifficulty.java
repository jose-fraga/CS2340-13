package com.example.project.wordle.enums;

public enum WordDifficulty {
    FIVE("wordle/levels/Five.fxml");

    private String path;

    WordDifficulty(String path) {
        this.path = path;
    }

    public String getPath() { return this.path; }
}
