package com.example.project.wordle;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;

public class WordPrompt implements Scoreable {
    private int attemptLimit;
    private StopWatch time = new StopWatch();
    private TargetWord targetWord;
    private AttemptedWord[] attemptedWords;

    public WordPrompt(int wordLength, int attemptLimit) {
        this.attemptLimit = attemptLimit;
        String targetWord = ""; //DictionaryService.getWord(wordLength);
        this.targetWord = new TargetWord(targetWord.toLowerCase());
        this.attemptedWords = new AttemptedWord[attemptLimit];
    }

    //TODO: Vinay -- add the logic for checking a word here:

    public boolean attempt(String attempt) {
        attempt.toLowerCase(); // make sure to lowercase for consistency
        // create and append an AttemptedWord to the attemptedWords array
        // check if the attempt is correct
        return false; }

    @Override
    public int calculateScore() { return 0; }

}
