package com.example.project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML private BorderPane gamePane;
    @FXML private Label gameTitle;
    @FXML private ImageView playerSprite;
    @FXML private Label playerName;
    @FXML private int time;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("ENTERED: " + Helper.currentGame.title() + " Free Game Screen");
        try {
            Helper.setGamePane(gamePane);
            Helper.changeGameScreen(Helper.currentGame.gameFxmlPath());

            gameTitle.setText(Helper.currentGame.title());
            String spritePath = Helper.getPlayerInstance().getSpritePath();

            playerSprite.setImage(new Image(Main.class.getResourceAsStream(spritePath)));
            playerName.setText(Helper.getPlayerInstance().getName());
            System.out.println(Helper.currentGame.title() + " Game Started");
            TimerLogic timer = new TimerLogic();
            timer.startTimer(time);

        } catch (Exception e) {
            System.out.println("FAILURE: " + Helper.currentGame.title() + " Free Game Scrreen FXML Error");
            e.printStackTrace();
        }
    }

    @FXML
    public void quitGame(ActionEvent actionEvent) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }
}