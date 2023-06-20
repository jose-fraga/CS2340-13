package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;
import org.apache.commons.lang3.time.StopWatch;

public class Level {

    public static final int TIME_LIMIT = 30; // seconds
    private int levelNumber;
    private StopWatch timer;
    private Grid grid;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.timer = new StopWatch(); // new TimerLogic();
        this.timer.start();
        this.grid = new Grid(LevelDifficulty.EASY, levelNumber); // Hardcoding EASY here
    }

    public Grid getGrid() { return this.grid; }
    public StopWatch getTimer() { return this.timer; }

    public int getSecondsLeft() {
        int timeLeft = TIME_LIMIT - (int) this.timer.getTime() / 1000;
        if (timeLeft <= 0) {
            if (!this.timer.isStopped()) {
                this.timer.stop();
            }
            timeLeft = 0;
        }
        return timeLeft;
    }

    public void pause() { this.timer.suspend(); }
    public boolean isPaused() { return this.timer.isSuspended(); }
    public void resume() { this.timer.resume(); }
    public void restart() {
        this.grid = new Grid(LevelDifficulty.EASY, this.levelNumber);
        this.timer.reset();
        this.timer.start();
        this.grid = new Grid(LevelDifficulty.EASY, levelNumber); // Hardcoding EASY here
    }

    public void complete() {
        this.timer.stop();
    }
}
