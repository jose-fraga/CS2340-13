package com.example.project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GameScreenController implements Initializable {
//    @FXML private AnchorPane gameContainer;
    @FXML private BorderPane gamePane;
    @FXML private Text gameTitle;
    @FXML private ImageView playerSprite;
    @FXML private Text playerName;
    @FXML private int time;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
//            FXMLLoader loader = new FXMLLoader();
//            AnchorPane gameScreen = loader.load(Main.class.getResource(Helper.currentGame.gameFxmlPath()));
//            gameContainer = gameScreen;
            Helper.setGamePane(gamePane);
            Helper.changeGameScreen(Helper.currentGame.gameFxmlPath());
//            FXMLLoader loader = new FXMLLoader();
//            Parent root = loader.load(Main.class.getResource(Helper.currentGame.gameFxmlPath()));
//            gamePane.setCenter(root);

            gameTitle.setText(Helper.currentGame.title());
            String spritePath = Helper.getPlayerInstance().getSpritePath();

            playerSprite.setImage(new Image(Main.class.getResourceAsStream(spritePath)));
            playerName.setText(Helper.getPlayerInstance().getName());

            System.out.println(Helper.currentGame.title() + " Game Started");
            TimerLogic timer = new TimerLogic();
            timer.startTimer(time);

        } catch (Exception e) {
            System.out.println(Helper.currentGame.title() + " Game FXML issue");
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