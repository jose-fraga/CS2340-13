package com.example.project.wordle;

import org.apache.commons.lang3.time.StopWatch;

public class WordPrompt implements Scoreable {
    private int attemptLimit;
    private StopWatch time;
    public boolean attempt(String attempt) { return false; }

    @Override
    public int calculateScore() { return 0; }

}
