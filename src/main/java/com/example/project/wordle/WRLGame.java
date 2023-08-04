package com.example.project.wordle;

public class WRLGame {
    private static WRLGame instance;
    private final Life life;

    private WRLGame() {
        life = new Life();
    }

    public static synchronized WRLGame getInstance() {
        if (instance == null) {
            instance = new WRLGame();
        }
        return instance;
    }

    public Life getLife() { return this.life; }
}
