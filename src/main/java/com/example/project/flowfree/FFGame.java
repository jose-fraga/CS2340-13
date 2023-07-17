package com.example.project.flowfree;

public class FFGame {
    private volatile static FFGame game_instance;
    private Level level;

    private FFGame() {}

    public Level getLevel() { return this.level; }
    public void createLevel(int levelNumber) { this.level = new Level(levelNumber); }

    public static FFGame getGameInstance() {
        if (game_instance == null) {
            synchronized (FFGame.class) {
                if (game_instance == null) {
                    game_instance = new FFGame();
                }
            }
        }
        return game_instance;
    }
}
