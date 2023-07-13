package com.example.project.codenames;

import com.example.project.codenames.enums.RoundPhase;
import com.example.project.codenames.enums.Team;
import org.apache.commons.lang3.time.StopWatch;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Round {
    private Team currentTeamTurn;
    private RoundPhase currentPhase = RoundPhase.OPERATIVE; // switch between operative and spy master
    private String clue = "";
    private int maxGuessCount = 0;
    private int guessedCount = 0;
    private StopWatch timer = new StopWatch();
    private final int TIME_BUFFER = 15;

    private Team winner = null;

    private Set<Word> words = new HashSet<Word>(25);
    private Word assassinWord;
    public Round() {
        // TODO: start random team red or blue
        Team firstTurnTeam = this.currentTeamTurn = Team.RED;
        Team secondTurnTeam = Team.BLUE;

        // TODO: generate 25 words, assign 9/8 to red, 8/9 to blue, 7 to neutral, 1 to assassin
        Collections.shuffle(DictionaryService.gameWords);
        addType(0,10,Team.RED);
        addType(10,19,Team.BLUE);
        addType(19,24,Team.NEUTRAL);
        addType(24,25,Team.ASSASSIN);
    }

    private void addType(int start, int end, Team type) {
        for (int i = start; i < end; i++) {
            DictionaryService.gameWords.get(i).setType(type);
        }
    }

    public void start() {
        timer.start();
    }

    public void restart() {
        timer.stop();
        timer.reset();
    }

    public void guessWord(Word guessed) {
        guessed.select();
        guessedCount++;

        // TODO: check for game over, or turn end
        // guessed.getType() != currentTeamTurn
    }

    private void endTurn() {
        this.currentTeamTurn = this.currentTeamTurn == Team.RED ? Team.BLUE : Team.RED;
        this.guessedCount = 0;
        this.currentPhase = RoundPhase.SPY_MASTER; // TODO: can have observable for the phase and team switching (for ui)
    }

    private void enterOperativePhase() {
        this.currentPhase = RoundPhase.OPERATIVE;
    }

    private void endGame(Team winner) {
        this.currentPhase = RoundPhase.WINNER;
        this.winner = winner;
    }
}
