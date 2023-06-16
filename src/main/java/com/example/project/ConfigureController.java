package com.example.project;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigureController implements Initializable {
    @FXML private TextField avatarName;
    @FXML private Label avatarNameError;
    @FXML private ChoiceBox<String> characterSelect;
    private String[] characters = {
            Sprite.CHARACTER1.name(),
            Sprite.CHARACTER2.name(),
            Sprite.CHARACTER3.name(),
            Sprite.CHARACTER4.name()
    };

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        characterSelect.getItems().addAll(characters);
    }

    @FXML
    private void continueToGame(ActionEvent actionEvent) {
        String playerName = avatarName.getCharacters().toString();
        Sprite selectedSprite = Sprite.valueOf(characterSelect.getValue());
        Player player = Helper.getPlayerInstance();

        if (playerName.isBlank()) {
            avatarNameError.setVisible(true);
            return;
        }

        player.setName(playerName);
        player.setSpritePath(selectedSprite.path());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Helper.changeScreen(stage, Helper.currentGame.gameFxmlPath(), "CS2340 - " + Helper.currentGame.title() + " (Game)");
    }
}
