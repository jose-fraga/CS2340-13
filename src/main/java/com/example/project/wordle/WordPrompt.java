package com.example.project.wordle;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;

public class WordPrompt implements Scorable {
    private int attemptLimit;
    private StopWatch time;
    private TargetWord targetWord;
    private ArrayList<AttemptedWord> attemptedWords;

    public boolean attempt(String attempt) { return false; }

    @Override
    public int calculateScore() { return 0; }

}
