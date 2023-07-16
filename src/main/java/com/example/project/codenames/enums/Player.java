package com.example.project.codenames.enums;

public enum Player {
    SPY_MASTER("Give Your Operatives A Clue!", "codenames/components/InputBox.fxml"),
    OPERATIVE("Your Operatives Are Guessing Now...", "codenames/components/ClueBox.fxml");

    private final String hint;
    private final String path;

    Player(String hint, String path) {
        this.hint = hint;
        this.path = path;
    }

    public String getHint() { return this.hint; }
    public String getPath() { return this.path; }
}
