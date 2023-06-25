package com.example.project.wordle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WRLGameController implements Initializable {
    @FXML private GridPane gridPane;

    private int index;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populate();
        index = 0;
    }

    public void populate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                StackPane currPane = new StackPane();
                currPane.getChildren().add(new Label());
                currPane.setAlignment(Pos.CENTER);
                gridPane.add(currPane, i, j);
            }
        }
//        System.out.println(gridPane.getChildren());
    }

    @FXML public void handle(KeyEvent e) {
//        System.out.println(e.getCode() + " " + e.getCode().getChar());
        if (Character.isLetter(e.getCode().getChar().charAt(0))) {
            if (gridPane.getChildren().get(index) instanceof Group) {
                index++;
            }
//            ((Label) gridPane.getChildren().get(index)).setText(e.getCode().getChar());
            StackPane curr = (StackPane) gridPane.getChildren().get(index);
            ((Label) curr.getChildren().get(0)).setText(e.getCode().getChar());
            index++;
        } else if (e.getCode() == KeyCode.BACK_SPACE) {
//            ((Label) gridPane.getChildren().get(--index)).setText("");
            StackPane curr = (StackPane) gridPane.getChildren().get(--index);
            ((Label) curr.getChildren().get(0)).setText("");
        }
    }
}