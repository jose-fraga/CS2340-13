package com.example.project.wordle;

import javafx.scene.layout.StackPane;

public class LetterPane extends StackPane {
    public AttemptedLetter letter;

    public LetterPane(TargetWord targetWord, int letterIndex) {
        this.letter = new AttemptedLetter(targetWord, letterIndex);
    }

    public boolean check(String attempt) {

        return this.letter.check(attempt);
        // this.letter.getResult().getColor();
    }

//    public void changeColor(String color) {
//        switch(color) {
//            case "green":
//                setStyle
//                break;
//            case "yellow":
//                //set row and column to green
//                break;
//            case "white":
//                //set row and column to green
//                break;
//        }
//    }

}
