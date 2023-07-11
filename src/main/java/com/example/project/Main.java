package com.example.project;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
//        Helper.currentGame = Game.UNSELECTED;
//        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
        Helper.currentGame = Game.CODENAMES;
        Helper.changeScreen(stage, Game.CODENAMES.initialFxmlPath(), Game.CODENAMES.title());
    }

    public static void main(Stage args) {
        launch();
    }
}