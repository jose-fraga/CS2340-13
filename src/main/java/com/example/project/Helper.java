/**
 * Class to create and store utils reusable methods
 */

package com.example.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Helper {
    public static Game currentGame;
    private static Player primaryPlayer, secondaryPlayer;
    private static BorderPane gamePane;

    public static BorderPane getGamePane() { return gamePane; }
    public static void setGamePane(BorderPane pane) { gamePane = pane; }

    public static Player getPrimaryPlayer() {
        if (primaryPlayer == null) {
            primaryPlayer = new Player();
        }
        return primaryPlayer;
    }

    public static Player getSecondaryPlayer() {
        if (secondaryPlayer == null) {
            secondaryPlayer = new Player();
        }
        return secondaryPlayer;
    }

    public static FXMLLoader changeGameScreen(String path) {
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(Main.class.getResource(path));
            Parent root = loader.load();
            gamePane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }

    public static void changeScreen(Stage stage, String screenName, String stageTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(Helper.class.getResource(screenName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(stageTitle);
            stage.show();
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
