package com.example.project.codenames;

import com.example.project.Player;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

public class WordPane extends StackPane {
    private Word word;
    private ArrayList<Player> playerGuesses;

    public WordPane() {
        word = new Word("", false);
    }
}
