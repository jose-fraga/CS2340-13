package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;

public class Level {
    // Update class with timer
    private int levelNumber;
    public TimerLogic timer;
    private Grid grid;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.timer = new TimerLogic();
        this.grid = new Grid(LevelDifficulty.EASY, levelNumber); //Hardcoding EASY here
    }

    public Grid getGrid() { return this.grid; }

    public int getLevelNumber() { return this.levelNumber; }
    public void setLevelNumber(int number) { this.levelNumber = number; }

    public void pause() {

    }

    public void resume() {

    }

    public void restart() {

    }
}
