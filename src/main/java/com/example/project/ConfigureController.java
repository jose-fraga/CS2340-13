package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigureController implements Initializable {
    @FXML private TextField characterName;
    @FXML private Label characterNameError;
    @FXML private ChoiceBox<String> characterSelect;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("ENTERED: " + Helper.currentGame.title() + " Configuration Screen");
        characterSelect.getItems().addAll(Sprite.getCharacters());
        characterSelect.setValue(Sprite.getCharacters()[0]);
        characterName.setText(Helper.getPlayerInstance().getName());
    }

    @FXML
    private void continueToGame(ActionEvent e) {
        String playerName = characterName.getCharacters().toString();
        Sprite selectedSprite = Sprite.valueOf(characterSelect.getValue());
        Player player = Helper.getPlayerInstance();
        if (playerName.isBlank()) {
            characterNameError.setVisible(true);
            return;
        }
        player.setName(playerName);
        player.setSpritePath(selectedSprite.getPath());
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, "GameScreen.fxml", "CS2340 - " + Helper.currentGame.title() + " (Game)");
    }
}
