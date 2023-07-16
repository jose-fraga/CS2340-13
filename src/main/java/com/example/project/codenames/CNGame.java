package com.example.project.codenames;

public class CNGame {
    private static CNGame game_instance;
    private final Round round = new Round();

    private CNGame() {}

    public Round getRound() { return this.round; }

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

