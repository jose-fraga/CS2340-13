package com.example.project.wordle;

public class WRLGame {
    private static WRLGame instance;
    private Level level;

    private WRLGame() {}

    public static synchronized WRLGame getGameInstance() {
        if (instance == null) {
            synchronized (WRLGame.class) {
                if (instance == null) {
                    instance = new WRLGame();
                }
            }
        }
        return instance;
    }

    public Level getLevel() { return this.level; }

    public void createLevel(int letterCount, int wordCount, int attemptCount) { 
        this.level = new Level(letterCount, wordCount, attemptCount);
    }
}
