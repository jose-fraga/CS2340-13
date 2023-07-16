/**
 * Class to create and store utils reusable methods
 */

package com.example.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Helper {
    public static Game currentGame;
    private static Player currentPlayer, secondaryPlayer;
    private static BorderPane gamePane, cnGamePane;

    public static BorderPane getGamePane() { return gamePane; }
    public static void setGamePane(BorderPane pane) {
        gamePane = pane;
        if (currentGame == Game.CODENAMES) {
            gamePane.setPrefWidth(900);
            gamePane.setPrefHeight(600);
        }
    }

    public static BorderPane getCNGamePane() { return cnGamePane; }
    public static void setCNGamePane(BorderPane pane) { cnGamePane = pane; }

    public static Player getPlayerInstance() {
        if (currentPlayer == null) {
            currentPlayer = new Player();
        }
        return currentPlayer;
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
