package com.example.project.flowfree.controllers;

import com.example.project.Game;
import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.example.project.flowfree.Leaderboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.project.flowfree.Leaderboard.board;


public class LeaderController implements Initializable {
    @FXML private GridPane gridPane;


    public LeaderController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        gridPane.add(new Label("that the one"), 0, 0);
     //   gridPane.add(new Label(Leaderboard.getInstance().getTopTime()), 0, 1);
     //   gridPane.add(new Label(board.getNextTime()), 0, 1);
     //   gridPane.add(new Label(board.getLastTime()), 0, 2);

        System.out.println("TESTING");
    }
    @FXML public void quitGame(ActionEvent e) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }
}
