package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;
import org.apache.commons.lang3.time.StopWatch;

public class Level {
    public static int TIME_LIMIT;
    private final int levelNumber;
    private final StopWatch timer;
    private Grid grid;
    private LevelDifficulty difficulty;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        this.timer = new StopWatch(); // new TimerLogic();
        this.timer.start();
    }

    public Grid getGrid() { return this.grid; }
    public void setGrid(LevelDifficulty difficulty) {
        this.difficulty = difficulty;
        this.grid = new Grid(this.difficulty, levelNumber);
    }

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
        this.grid = new Grid(this.difficulty, levelNumber);
    }

    public void complete() {
        this.timer.stop();
    }
}
