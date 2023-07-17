package com.example.project.codenames.controllers;

import com.example.project.Helper;
import com.example.project.codenames.CNGame;

public class CNWinScreenController {
    public void restartGame() {
        CNGame.getGameInstance().clearRound();
        Helper.changeGameScreen("codenames/CNBufferScreen.fxml");
    }
}
