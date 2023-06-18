package com.example.project;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Helper.currentGame = Game.UNSELECTED;
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
//        Helper.changeScreen(stage, Game.FLOW.initialFxmlPath(), Game.FLOW.title());
//        Helper.currentGame = Game.FLOW;
//        Helper.changeScreen(stage, Helper.currentGame.gameFxmlPath(), "CS2340 - " + Helper.currentGame.title() + " (Game)");
    }

    public static void main(Stage args) {
        launch();
    }
}