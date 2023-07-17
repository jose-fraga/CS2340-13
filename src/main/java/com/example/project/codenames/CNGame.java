package com.example.project.codenames;

public class CNGame {
    private static CNGame game_instance;
    private Round round;

    private CNGame() {}

    public Round getRound() {
        if (this.round == null) {
            this.round = new Round();
        }
        return this.round;
    }
    public void clearRound() { this.round = null; }

    public static CNGame getGameInstance() {
        if (game_instance == null) {
            synchronized (CNGame.class) {
                if (game_instance == null) {
                    game_instance = new CNGame();
                }
            }
        }
        return game_instance;
    }
}

