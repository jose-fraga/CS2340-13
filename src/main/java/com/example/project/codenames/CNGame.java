package com.example.project.codenames;

public class CNGame {
    private static CNGame game_instance;
    private Round round;

    private CNGame() {}

    public Round getRound() { return this.round; }
    public void createRound() { this.round = new Round(); }

    public static synchronized CNGame getGameInstance() {
        if (game_instance == null) {
            game_instance = new CNGame();
        }
        return game_instance;
    }
}

