package com.example.project.codenames;

import com.example.project.codenames.enums.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Round {
    private Team activeTeam, passiveTeam;
    private ArrayList<Word> words;

    public Round() {
        this.activeTeam = new Random().nextBoolean() ? new Team(Type.RED, 9) : new Team(Type.BLUE, 9);
        this.passiveTeam = (activeTeam.getType() == Type.RED) ? new Team(Type.BLUE, 8) : new Team(Type.RED, 8);
        updateWordType();
    }

    public ArrayList<Word> getWords() { return this.words; }

    private void updateWordType() {
        this.words = DictionaryService.getGameWords();
        addType(0, 9, activeTeam.getType());
        addType(9, 17, passiveTeam.getType());
        this.words.get(24).setType(Type.ASSASSIN);
        Collections.shuffle(this.words);
    }

    private void addType(int start, int end, Type type) {
        for (int i = start; i < end; i++) {
            this.words.get(i).setType(type);
        }
    }

//    public void guessWord(Word guessed) {
//        guessed.select();
//        guessedCount++;
//
//        // TODO: check for game over, or turn end
//        // guessed.getType() != currentTeamTurn
//    }

//    private void endTurn() {
////        this.currentTeamTurn = this.currentTeamTurn == CardType.RED ? CardType.BLUE : CardType.RED;
//        this.guessedCount = 0;
//        this.currentPhase = RoundPhase.SPY_MASTER; // TODO: can have observable for the phase and team switching (for ui)
//    }
//    private void enterOperativePhase() {
//        this.currentPhase = RoundPhase.OPERATIVE;
//    }
//
//    private void endGame(CardType winner) {
//        this.currentPhase = RoundPhase.WINNER;
//        this.winner = winner;
//    }
}
