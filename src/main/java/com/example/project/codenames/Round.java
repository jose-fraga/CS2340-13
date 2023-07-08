package com.example.project.codenames;

import org.apache.commons.lang3.time.StopWatch;

public class Round {
    private int currTurn;
    private Word clue;
    private int clueCount;
    private StopWatch timer;
    private int guesses;

    private final int TIME_BUFFER = 15;

    public void start() {
        timer.start();
    }
    public void restart() {
        timer.stop();
        timer.reset();
    }
    public void nextTurn() {
     //   While(guesses < clueCount + 1) {
    //        guess();
    //}
        //switch screen
    }
}
