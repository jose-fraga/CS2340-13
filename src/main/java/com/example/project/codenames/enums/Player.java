package com.example.project.codenames.enums;

public enum Player {
    SPY_MASTER("Spymaster", "Give Your Operatives A Clue!", "codenames/components/ClueInput.fxml"),
    OPERATIVE("Operative", "Your Operatives Are Guessing Now...", "codenames/components/ClueDisplay.fxml");

    private final String name;
    private final String hint;
    private final String path;

    Player(String name, String hint, String path) {
        this.name = name;
        this.hint = hint;
        this.path = path;
    }

    public String getName() { return this.name; }
    public String getHint() { return this.hint; }
    public String getPath() { return this.path; }
}
