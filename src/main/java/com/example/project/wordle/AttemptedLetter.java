package com.example.project.wordle;

import com.example.project.wordle.enums.Status;

public class AttemptedLetter {
    private Status status = Status.EMPTY;
    private String letter;
    private int targetIdx;
    private static String locale = "";

    public AttemptedLetter(int index) {
        this.targetIdx = index;
    }

    public void setLetter(String letter) { this.letter = letter; }

    public Status getStatus() { return this.status; }

    public void checkAttempt(int wordCount) {
        if(wordCount == 4) {
            locale = "RICE";
        } else if(wordCount == 5) {
            locale = "APPLE";
        } else {
            locale = "ACROSS";
        }
        if (letter.equals(locale.substring(targetIdx,targetIdx+1))) {
            status = Status.CORRECT;
        } else if (locale.contains(letter)) {
            status = Status.PARTIAL;
        } else {
            status = Status.INCORRECT;
        }
    }

    @Override
    public String toString() {
        return "AttemptedLetter{" +
                "status=" + status +
                ", letter='" + letter + '\'' +
                ", targetIdx=" + targetIdx +
                '}';
    }
}
