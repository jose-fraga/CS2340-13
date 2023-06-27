package com.example.project.wordle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WRLGameController implements Initializable {
    @FXML private GridPane gridPane;

    private int x = 0, y = 0;
    // Update this with AttemptedWord
    private String currWord;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currWord = "";
    }

    @FXML public void handle(KeyEvent e) {
        int index = gridPane.getChildren().size() - 1;
        if (Character.isLetter(e.getCode().getChar().charAt(0))) {
            if (x == 5 && gridPane.getChildren().get(index) != null) {
                return;
            }
            StackPane curr = new StackPane();
            curr.setAlignment(Pos.CENTER);
            curr.getChildren().add(new Label(e.getCode().getChar()));
            gridPane.add(curr, x, y);
            x++;
            currWord += e.getCode().getChar();
        } else if (e.getCode() == KeyCode.BACK_SPACE) {
            if (x == 0 && (index%5 == 0 || gridPane.getChildren().get(index) == null)) {
                return;
            }
            gridPane.getChildren().remove(index);
            x--;
            currWord = currWord.substring(0,currWord.length()-1);
        } else if (e.getCode() == KeyCode.ENTER) {
            if (x != 5) {
                return;
            }
            System.out.println(DictionaryService.checkValidity(currWord.toLowerCase()));
            // if false reset this specific row
            currWord = "";
            x = 0;
            y++;
        }
    }
}
