package com.example.project.wordle;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;

public class Level implements Scoreable {

    public static final int TIME_LIMIT = 30; // seconds
    private int levelNumber;
    private StopWatch timer = new StopWatch();

    private ArrayList<WordPrompt> wordPrompts = new ArrayList<WordPrompt>();

    public Level(int wordLength, int wordCount, int attemptLimit) {
        this.timer.start();

        // initialize wordCount number of wordPrompts of the correct wordLength and attemptLimit
        for (int i = 0; i < wordCount; i++) {
            this.wordPrompts.add(new WordPrompt(wordLength, attemptLimit));
        }
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
        this.timer.reset();
        this.timer.start();
        // potentially add logic to restart words / attempts
    }

    public void complete() {
        this.timer.stop();
    }

    @Override
    public int calculateScore() {
        return 0;
    }
}
