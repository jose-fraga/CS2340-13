package com.example.project.flowfree;

import com.example.project.Helper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FlowfreeController implements Initializable {
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;

    private FlowfreeGame gameInstance;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Flow Free Started!");
        this.gameInstance = FlowfreeGame.getInstance();
    }

    @FXML private void selectLevel(ActionEvent e) {
        int levelNumber = Integer.parseInt(((Button) e.getSource()).getText()) - 1;
        this.gameInstance.selectLevel(levelNumber);

//        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//        Helper.changeScreen(stage, "flowfree/levels/easy.fxml", "Flow Free (Difficulty - EASY)");
        Helper.changeGameScreen("flowfree/levels/easy.fxml");
    }
}
