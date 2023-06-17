package com.example.project.flowfree;

public class FlowfreeGame {
    private static FlowfreeGame game_instance;
    public int selectLevel(int level) {
        return 1;
    }

    public void exit() {}

    public static synchronized FlowfreeGame getInstance() {
        if (game_instance == null) {
            game_instance = new FlowfreeGame();
        }
        return game_instance;
    }
}

