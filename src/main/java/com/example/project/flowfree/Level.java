package com.example.project.flowfree;


import com.example.project.flowfree.enums.LevelDifficulty;

public class Level {
    private int levelNumber;
    private int timer;

    private Grid grid;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.timer = 0;
        this.grid = new Grid(LevelDifficulty.EASY, levelNumber); //Hardcoding EASY here
    }

    public Grid getGrid() {
        return this.grid;
    }

    public int getLevelNumber() { return this.levelNumber; }
    public void setLevelNumber(int number) { this.levelNumber = number; }

    public boolean isCompleted() {
        return true;
    }

    public void pause() {
    }

    public void resume() {
    }

    public void restart() {
    }

    public void quit() {
    }
}
