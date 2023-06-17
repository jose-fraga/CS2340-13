/**
 * Class to create and store utils reusable methods
 */

package com.example.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Helper {
    public static Game currentGame;
    private static Player currentPlayer;

    public static Player getPlayerInstance() {
        if (currentPlayer == null) {
            currentPlayer = new Player();
        }
        return currentPlayer;
    }

    public static void changeScreen(Stage stage, String screenName, String stageTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(Helper.class.getResource(screenName));
            Parent root = loader.load();
            Scene scene = new Scene(root,850,850);
            stage.setScene(scene);
            stage.setTitle(stageTitle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
