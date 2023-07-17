package com.example.project.codenames;

public class CNGame {
    private static CNGame game_instance;
    private Round round;

    private CNGame() {
        DictionaryService.populate();
        round = new Round();
    }

    public Round startNewRound() {
        this.round = new Round();
        return this.round;
    }

    public Round getRound() {
        return this.round;
    }

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

