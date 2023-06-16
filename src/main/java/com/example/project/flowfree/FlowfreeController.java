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

    private Level level;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Flow Free Game Started");
        level = new Level();
    }

    @FXML private void selectLevel(ActionEvent e) {
        level.setLevelNumber(Integer.parseInt(((Button) e.getSource()).getText()));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "flowfree/levels/level1.fxml", "Level #1");
    }
}
