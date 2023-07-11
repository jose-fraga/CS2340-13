package com.example.project.codenames.controllers;

import com.example.project.Game;
import com.example.project.Helper;
import com.example.project.wordle.LetterPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class CNSetupScreenController implements Initializable {
    @FXML private ListView<StackPane> redView, blueView;
    @FXML private Label redLabel, blueLabel;
    @FXML private TextField textField;

    private int team;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("ENTERED: Codenames Configuration Screen");
    }

    @FXML private void add(ActionEvent e) {
        StackPane test = new StackPane();
        test.getChildren().add(new Label(textField.getText()));
        test.setAlignment(Pos.CENTER);

        String text = ((Button) e.getSource()).getText();
        if (text.equals("Add to Red")) {
            redView.getItems().add(test);
        } else {
            blueView.getItems().add(test);
        }
        handle();
    }

    private void handle() {
        redView.getItems().forEach(item -> {
            item.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                System.out.println("NOT: " + e.getSource());
                if (e.getSource() instanceof StackPane) {
                    StackPane test = (StackPane) e.getSource();
                    System.out.println(test.getChildren().get(0));
                    redLabel.setText(((Label) test.getChildren().get(0)).getText());
                }
            });
        });
        blueView.getItems().forEach(item -> {
            item.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                System.out.println("NOT: " + e.getSource());
                if (e.getSource() instanceof StackPane) {
                    StackPane test = (StackPane) e.getSource();
                    System.out.println(test.getChildren().get(0));
                    blueLabel.setText(((Label) test.getChildren().get(0)).getText());
                }
            });
        });
    }

    @FXML private void continueToGame(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Game.CODENAMES.gameFxmlPath(), "CS2340 - " + Helper.currentGame.title() + " (Game)");
    }
}
