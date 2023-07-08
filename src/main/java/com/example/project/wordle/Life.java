package com.example.project.wordle;

public class Life {
    private int lives;

    public Life() {
        this.lives = 6;
    }

    public int getLives() { return this.lives; }

    public void calculateLives(String correctWord, String attemptedWord) {
        if (!correctWord.equals(attemptedWord)) {
            this.lives--;
        }
    }
}
