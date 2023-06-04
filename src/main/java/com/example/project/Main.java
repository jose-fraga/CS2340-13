package com.example.project;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Helper.changeScreen(stage, "SelectScreen.fxml", "CS2340 Project");
    }

    public static void main(Stage args) {
        launch();
    }
}