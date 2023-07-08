package com.example.project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class SelectController {
    public void flowSwitch(ActionEvent e) {
        selectGame(e, Game.FLOW);
    }

    public void wordleSwitch(ActionEvent e) {
        selectGame(e, Game.WORDLE);
    }

    public void codeNameSwitch(ActionEvent e) {
        selectGame(e, Game.CODENAMES);
    }

    private void selectGame(ActionEvent e, Game selectedGame) {
        System.out.println("ENTERED: CS2340 Game Select Screen");
        Helper.currentGame = selectedGame;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Helper.currentGame.initialFxmlPath(), "CS2340 - " + Helper.currentGame.title() + " (Splash)");
    }
}
