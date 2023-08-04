package com.example.project.wordle.controllers;

import com.example.project.Game;
import com.example.project.Helper;
import com.example.project.wordle.Leaderboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class WRLLeaderboardController implements Initializable {
    @FXML private GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: Wordle Leaderboard Screen");
        populate();
    }

    private void populate() {
        List<Map.Entry<String, Integer>> sortedList = Leaderboard.getInstance().getTopScores();
        for (int i = 0; i < sortedList.size(); i++) {
            Map.Entry<String, Integer> curr = sortedList.get(i);
            StackPane entryName = createPane(curr.getKey()), entryScore = createPane(String.valueOf(curr.getValue()));
            gridPane.add(entryName,1,(i+1));
            gridPane.add(entryScore, 2,(i+1));
            if (i == 2) { break; }
        }
        System.out.println(List.of(Leaderboard.getInstance().getTopScores()));
    }

    private StackPane createPane(String text) {
        StackPane currPane = new StackPane();
        Label currLabel = new Label(text);
        currLabel.setFont(Font.font("Berlin Sans FB Demi", FontWeight.BOLD, 14));
        currPane.getChildren().add(currLabel);
        currPane.setAlignment(Pos.CENTER);
        return currPane;
    }

    @FXML public void returnToLevelSelect(ActionEvent e) {
        Helper.changeGameScreen(Helper.currentGame.gameFxmlPath());
    }

    @FXML public void quitGame(ActionEvent e) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }
}
