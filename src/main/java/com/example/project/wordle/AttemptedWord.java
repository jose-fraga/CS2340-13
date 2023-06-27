package com.example.project.wordle;

import com.example.project.wordle.enums.AttemptedResult;

import java.util.ArrayList;


public class AttemptedWord extends Word implements Checkable {
    private AttemptedLetter[] letterList;
    public AttemptedResult attemptedResult = AttemptedResult.UNKNOWN;

    public AttemptedWord(TargetWord target, String word) {
        super(word);
        this.letterList = new AttemptedLetter[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letterList[i] = new AttemptedLetter(target, i, this.letterAt(i));
        }
    }

    @Override
    public boolean check() {
        boolean result = true;
        for (int i = 0; i < this.getWord().length() && result; i++) {
            result = result && letterList[i].check();
        }
        this.attemptedResult = result ? AttemptedResult.CORRECT : AttemptedResult.INCORRECT;
        return result;
    }
}
