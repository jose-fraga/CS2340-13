package com.example.project.wordle;

import com.example.project.wordle.enums.Status;

public class AttemptedLetter {
    private final int targetIdx;
    private String letter;
    private Status status = Status.EMPTY;

    public AttemptedLetter(int index) {
        this.targetIdx = index;
    }

    public void setLetter(String letter) { this.letter = letter; }

    public Status getStatus() { return this.status; }

    public void checkInitial(Word attemptedWord, TargetWord targetWord) {
        char attemptedLetter = this.letter.charAt(0);
        char targetLetter = targetWord.word.charAt(this.targetIdx);

        if (attemptedLetter != targetLetter && !targetWord.getLetterMap().containsKey(attemptedLetter)) {
            this.status = Status.INCORRECT;
            attemptedWord.getLetterMap().remove(attemptedLetter);
        } else if (attemptedLetter == targetLetter) {
            this.status = Status.CORRECT;
            targetWord.updateLetterMap(targetLetter);
            if (!targetWord.getLetterMap().containsKey(attemptedLetter)) {
                attemptedWord.getLetterMap().remove(attemptedLetter);
            }
        }
    }

    public void checkNext(Word attemptedWord, TargetWord targetWord) {
        char attemptedLetter = this.letter.charAt(0);

        if (this.status == Status.EMPTY) {
            if (!attemptedWord.getLetterMap().containsKey(attemptedLetter)) {
                this.status = Status.INCORRECT;
            } else {
                this.status = Status.PARTIAL;
                targetWord.updateLetterMap(attemptedLetter);
                if (!targetWord.getLetterMap().containsKey(attemptedLetter)) {
                    attemptedWord.getLetterMap().remove(attemptedLetter);
                }
            }
        }
    }
}
