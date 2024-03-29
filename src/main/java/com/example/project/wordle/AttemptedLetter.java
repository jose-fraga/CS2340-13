package com.example.project.wordle;

import com.example.project.wordle.enums.Status;

public class AttemptedLetter {
    private Status status = Status.EMPTY;
    private String letter;
    private final int targetIdx;

    public AttemptedLetter(int index) {
        this.targetIdx = index;
    }

    public void setLetter(String letter) { this.letter = letter; }

    public Status getStatus() { return this.status; }

    public void checkAttempt(String targetWord) {
        if (letter.equals(targetWord.substring(targetIdx,targetIdx+1))) {
            status = Status.CORRECT;
        } else if (targetWord.contains(letter)) {
            status = Status.PARTIAL;
        } else {
            status = Status.INCORRECT;
        }
    }
}
