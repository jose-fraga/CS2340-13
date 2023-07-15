package com.example.project.codenames.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CNController implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Codenames Game Started");

        /*
          1) wordset = 25 wordset arrangement
          2) gameTurn = <Team.RED, Team.BLUE>
          3) firstTeamWords = 9 words assigned to gameTurn team
          4) secondTeamWords = 8 words for other team
          5) neutralWords = choose remaining words as bystanders
          6) gameState = <CodenamesState.STARTING, CodenamesState.CREATING_CLUE, CodenamesState.GUESSING, CodenamesState.NEXT_SPYMASTER...>
        */
    }

    public void processClue(String clue, int guessCount) {
        // Process the received clue and guess count
        // Implement the logic here based on the clue and guess count provided

        System.out.println("Received clue: " + clue);
        System.out.println("Received guess count: " + guessCount);

        // Here we perform further processing or update the game state accordingly
    }

}
