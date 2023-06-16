package com.example.project.flowfree;

import com.example.project.Game;
import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FlowfreeSplashScreenController implements Initializable {
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Flow Free Main Start Screen");
    }

    @FXML
    public void backToGameSelect(ActionEvent actionEvent) {
        Helper.currentGame = Game.UNSELECTED;
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.UNSELECTED.initialFxmlPath(), Game.UNSELECTED.title());
    }

    @FXML
    private void configureGame(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "ConfigureScreen.fxml", "CS2340 " + Helper.currentGame.title() + " Configuration");
    }
}
