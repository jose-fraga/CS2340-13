package com.example.project.codenames;

import com.example.project.Game;
import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CodenamesController implements Initializable {

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

    @FXML
    public void backToGameSelect(ActionEvent actionEvent) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }
}
