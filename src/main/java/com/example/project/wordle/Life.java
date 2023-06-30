package com.example.project.wordle;

import com.example.project.wordle.enums.Status;

public class Life {
    private int lives;

    public Life() {
        this.lives = 6;
    }

    public void calculateLives(String correctWord, String attemptedWord) {
        if (!correctWord.equals(attemptedWord)) {
            lives--;
        }
    }

    public int getLives() {
        return lives;
    }
}
