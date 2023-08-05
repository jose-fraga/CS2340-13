package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CRGameController implements Initializable {
    @FXML private BorderPane gamePane;
    @FXML private Label gameTitle, playerName;
    @FXML private ImageView playerSprite;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            System.out.println("ENTERED: " + Helper.currentGame.title() + " Game Screen");

            if (Helper.currentGame == Game.CODENAMES) {
                gamePane.setPrefWidth(895);
                gamePane.setPrefHeight(650);
            }

            Helper.setGamePane(gamePane);
            Helper.changeGameScreen(Helper.currentGame.gameFxmlPath());

            gameTitle.setText(Helper.currentGame.title());
            String spritePath = Helper.getPrimaryPlayer().getSpritePath();

            playerSprite.setImage(new Image(Main.class.getResourceAsStream(spritePath)));
            playerName.setText(Helper.getPrimaryPlayer().getName());

            if (Helper.currentGame == Game.CODENAMES) {
                HBox playersBox = (HBox) ((HBox) gamePane.getTop()).getChildren().get(2);
                FXMLLoader curr = new FXMLLoader(Main.class.getResource("components/PlayerBox.fxml"));
                playersBox.getChildren().add(curr.load());

                VBox currBox = (VBox) playersBox.getChildren().get(1);
                spritePath = Helper.getSecondaryPlayer().getSpritePath();
                ((ImageView) currBox.getChildren().get(0)).setImage(new Image(Main.class.getResourceAsStream(spritePath)));
                ((Label) currBox.getChildren().get(1)).setText(Helper.getSecondaryPlayer().getName());
            }
        } catch (Exception e) {
            System.out.println("FAILURE: " + Helper.currentGame.title() + " Game Screen FXML Error!");
            e.printStackTrace(System.out);
        }
    }

    @FXML public void quitGame(ActionEvent e) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }
}
