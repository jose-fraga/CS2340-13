package com.example.project.codenames.controllers;

import com.example.project.Helper;
import com.example.project.Sprite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CNSetupScreenController implements Initializable {
    @FXML private ListView<StackPane> redView, blueView;
    @FXML private Label redLabel, blueLabel;
    @FXML private TextField textField;
    @FXML private ChoiceBox<String> redCharacterSelect, blueCharacterSelect;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("ENTERED: Codenames Configuration Screen");
        redCharacterSelect.getItems().addAll(Sprite.getCharacters());
        blueCharacterSelect.getItems().addAll(Sprite.getCharacters());

        redCharacterSelect.setValue(Sprite.getCharacters()[0]);
        blueCharacterSelect.setValue(Sprite.getCharacters()[0]);
    }

    @FXML private void add(ActionEvent e) {
        if (!textField.getText().isEmpty()) {
            StackPane curr = new StackPane();
            curr.getChildren().add(new Label(textField.getText()));
            curr.setAlignment(Pos.CENTER);

            String text = ((Button) e.getSource()).getText();
            if (text.equals("Add to Red")) {
                redView.getItems().add(curr);
            } else {
                blueView.getItems().add(curr);
            }
            textField.clear();
            handle();
        }
    }

    private void handle() {
        redView.getItems().forEach(item -> handleClicked(item, 0));
        blueView.getItems().forEach(item -> handleClicked(item, 1));
    }

    private void handleClicked(StackPane item, int team) {
        item.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getSource() instanceof StackPane) {
                StackPane curr = (StackPane) e.getSource();
                String text = ((Label) curr.getChildren().get(0)).getText();
                if (team == 0) {
                    redLabel.setText(text + "'s Selected:");
                    Helper.getPlayerInstance().setName(text);
                } else {
                    blueLabel.setText(text + "'s Selected:");
                    Helper.getSecondaryPlayer().setName(text);
                }
            }
        });
    }

    private void updatePlayerSprites() {
        Helper.getPlayerInstance().setSpritePath(Sprite.valueOf(redCharacterSelect.getValue()).getPath());
        Helper.getSecondaryPlayer().setSpritePath(Sprite.valueOf(blueCharacterSelect.getValue()).getPath());
    }

    @FXML private void continueToGame(ActionEvent e) {
        updatePlayerSprites();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "GameScreen.fxml", "CS2340 - " + Helper.currentGame.title() + " (Game)");
    }
}
