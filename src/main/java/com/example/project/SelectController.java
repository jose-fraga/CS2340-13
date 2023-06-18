package com.example.project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class SelectController {
    public void flowSwitch(ActionEvent actionEvent) {
        selectGame(actionEvent, Game.FLOW);
    }

    public void twentySwitch(ActionEvent actionEvent) {
        selectGame(actionEvent, Game.TWENTY_FOURTY_EIGHT);
    }

    public void codeNameSwitch(ActionEvent actionEvent) {
        selectGame(actionEvent, Game.CODENAMES);
    }

    private void selectGame(ActionEvent actionEvent, Game selectedGame) {
        System.out.println("ENTERED: CS2340 Game Select Screen");
        Helper.currentGame = selectedGame;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Helper.currentGame.initialFxmlPath(), "CS2340 - " + Helper.currentGame.title() + " (Splash)");
    }
}
