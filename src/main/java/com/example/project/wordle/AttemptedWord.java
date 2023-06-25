package com.example.project.wordle;

import com.example.project.wordle.enums.AttemptedResult;

import java.util.ArrayList;


public class AttemptedWord extends Word {

    public AttemptedWord(String word) {
        super(word);
    }

    ArrayList <AttemptedLetter> letterList = new ArrayList<AttemptedLetter>();

   // public AttemptedResult getAttemptResult() {
   // }

}
