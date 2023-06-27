package com.example.project.wordle;

import com.example.project.wordle.enums.AttemptedResult;

public class AttemptedLetter implements Checkable {
    private String attemptedLetter;
    private int targetIndex;
    private TargetWord targetWord;
    private AttemptedResult result;

    public AttemptedLetter(TargetWord targetWord, int targetIndex) {
        this(targetWord, targetIndex, "");
    }

    public AttemptedLetter(TargetWord targetWord, int targetIndex, String letter) {
        this.targetWord = targetWord;
        this.targetIndex = targetIndex;
        this.attemptedLetter = letter;
        this.result = AttemptedResult.UNKNOWN;
    }

    public void setAttemptedLetter(String letter) {
        attemptedLetter = letter;
    }

    public String getAttemptedLetter() {
        return attemptedLetter;
    }

    public void setResult(AttemptedResult result) {
        this.result = result;
    }

    public AttemptedResult getResult() {
        return this.result;
    }


    @Override
    public boolean check() {
        if (targetWord.letterAt(targetIndex).equalsIgnoreCase(attemptedLetter)){
            result = AttemptedResult.CORRECT;
            return true;
        } else if (attemptedLetter.isEmpty()) {
            result = AttemptedResult.UNKNOWN;
        } else if (targetWord.hasLetter(attemptedLetter)) {
            result = AttemptedResult.PARTIAL;
        } else {
            result = AttemptedResult.INCORRECT;
        }

        return false;
    }
}
