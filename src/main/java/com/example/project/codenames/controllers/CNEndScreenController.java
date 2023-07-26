package com.example.project.codenames.controllers;

import com.example.project.Game;
import com.example.project.Helper;
import com.example.project.codenames.CNGame;
import com.example.project.codenames.enums.Type;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CNEndScreenController implements Initializable  {
    @FXML public Label winningTeamLabel, scoreDisplay;
    @FXML public AnchorPane anchorPane;
    @FXML public ImageView backgroundIV;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("ENTERED: " + Helper.currentGame.title() + "End Screen");
        changeLabel();
        addBackground();
        updateScore();
    }

    @FXML public void quitGame(ActionEvent e) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }

    public void changeLabel() {
        if(CNGame.getGameInstance().getRound().getActiveTeam().getType() == Type.RED) {
            winningTeamLabel.setTextFill(Paint.valueOf("#CF2129"));
            winningTeamLabel.setText("RED");
        } else if (CNGame.getGameInstance().getRound().getActiveTeam().getType() == Type.BLUE) {
            winningTeamLabel.setTextFill(Paint.valueOf("#03B8D0"));
            winningTeamLabel.setText("BLUE");
        }
    }

    public void addBackground() {
        anchorPane.setStyle("-fx-background-image:url(@images/CNEnd.png)");
        backgroundIV.fitWidthProperty().bind(anchorPane.widthProperty());
        backgroundIV.fitHeightProperty().bind(anchorPane.heightProperty());
    }

    public void updateScore() {
        int score = CNGame.getGameInstance().getRound().getActiveTeam().getNumOfCards() * 10;
        scoreDisplay.setText("Latest Score: " + score);
        CNGame.getGameInstance().clearRound();
    }
}
