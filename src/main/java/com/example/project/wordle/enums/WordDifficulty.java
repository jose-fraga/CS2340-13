package com.example.project.wordle.enums;

public enum WordDifficulty {
    FOUR("wordle/levels/Four.fxml"),
    FIVE("wordle/levels/Five.fxml"),
    SIX("wordle/levels/Six.fxml");




    private String path;

    WordDifficulty(String path) {
        this.path = path;
    }

    public String getPath() { return this.path; }
}
