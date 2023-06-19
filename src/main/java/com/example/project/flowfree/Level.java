package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;
import org.apache.commons.lang3.time.StopWatch;

public class Level {
    // Update class with timer
    private int levelNumber;
//    public TimerLogic timer;
    private StopWatch timer;
    private Grid grid;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.timer = new StopWatch(); //new TimerLogic();
        this.timer.start();
        this.grid = new Grid(LevelDifficulty.EASY, levelNumber); //Hardcoding EASY here
    }

    public Grid getGrid() { return this.grid; }

    public StopWatch getTimer() { return this.timer; }

    public void pause() {
        this.timer.suspend();
    }

    public boolean isPaused() {
        return this.timer.isSuspended();
    }

    public void resume() {
        this.timer.resume();
    }

    public void restart() {
        this.grid = new Grid(LevelDifficulty.EASY, this.levelNumber);
        this.timer.reset();
    }
}
