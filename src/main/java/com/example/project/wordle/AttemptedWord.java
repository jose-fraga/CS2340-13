package com.example.project.wordle;

import java.util.ArrayList;

public class AttemptedWord extends Word {
    ArrayList <AttemptedLetter> letterList = new ArrayList<AttemptedLetter>();

    public AttemptedWord(String word) {
        super(word);
    }
}
