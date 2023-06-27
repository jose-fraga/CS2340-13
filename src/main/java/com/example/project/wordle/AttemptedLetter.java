package com.example.project.wordle;

import com.example.project.wordle.enums.AttemptedResult;

public class AttemptedLetter {
    private String attemptedLetter = "";
    public AttemptedResult result = AttemptedResult.UNKNOWN;

    private TargetWord target;
    private int targetIndex;

    public AttemptedLetter(TargetWord target, int targetIndex) {
        this.target = target;
        this.targetIndex = targetIndex;
    }

    public boolean check(String attempt) {
        boolean result = false;
        this.attemptedLetter = attempt;
        String correctLetter = target.getLetter(targetIndex);

        if (attempt == correctLetter) {
            this.result = AttemptedResult.CORRECT;
            result = true;
        } else if (this.target.getWord().contains(attempt)){
            this.result = AttemptedResult.PARTIAL;
        } else {
            this.result = AttemptedResult.INCORRECT;
        }
        return result;
    }

    public void clear() {
        this.attemptedLetter = "";
        this.result = AttemptedResult.UNKNOWN;
    }
}
