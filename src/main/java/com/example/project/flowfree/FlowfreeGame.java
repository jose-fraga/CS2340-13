package com.example.project.flowfree;

public class FlowfreeGame {
    private static FlowfreeGame game_instance;
    private Level level;

    private FlowfreeGame() {}

    public Level selectLevel(int levelNumber) {
        this.level = new Level(levelNumber);
        return this.level;
    }

    public Level getLevel() {
        return this.level;
    }

    public void exit() {}

    public static synchronized FlowfreeGame getInstance() {
        if (game_instance == null) {
            game_instance = new FlowfreeGame();
        }
        return game_instance;
    }
}

