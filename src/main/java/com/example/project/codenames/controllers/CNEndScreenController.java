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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;

import java.util.ResourceBundle;


public class CNEndScreenController implements Initializable {
    @FXML public Label label;
    @FXML public AnchorPane anchor;
    @FXML public ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchor.setStyle("-fx-background-image:url(@images/CNEnd.png)");
        System.out.println("ENTERED: " + Helper.currentGame.title() + "End Screen");
        imageView.fitWidthProperty().bind(anchor.widthProperty());
        imageView.fitHeightProperty().bind(anchor.heightProperty());
        changeLabel();
    }

    @FXML public void quitGame(ActionEvent e) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }

     public void changeLabel() {
        if(CNGame.getGameInstance().getRound().getActiveTeam().getType() == Type.RED) {
            label.setTextFill(Paint.valueOf("#CF2129"));
        } else if (CNGame.getGameInstance().getRound().getActiveTeam().getType() == Type.BLUE) {
            label.setTextFill(Paint.valueOf("#CF2129"));
        }
        label.setText(" " + CNGame.getGameInstance().getRound().getActiveTeam().getType());
    }




}
