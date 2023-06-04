package com.example.project;

public enum Game {
    UNSELECTED("CS2340 Project",
            "SelectScreen.fxml",
            "SelectScreen.fxml"),
    FLOW("Flow",
            "FlowScreen.fxml",
            "FlowGameScreen.fxml"),
    TWENTY_FOURTY_EIGHT("2048",
            "2048Screen.fxml",
            "2048GameScreen.fxml"),
    CODENAMES("Codenames",
            "codenames/CodenamesSplashScreen.fxml",
            "codenames/CodenamesInitialGamePlayScreen.fxml");

    private final String title;
    private final String initialFxmlPath;
    private final String gameFxmlPath;

    Game(String title, String initialFxmlPath, String gameFxmlPath) {
        this.title = title;
        this.initialFxmlPath = initialFxmlPath;
        this.gameFxmlPath = gameFxmlPath;
    }

    public String title() {
        return this.title;
    }

    public String initialFxmlPath() {
        return this.initialFxmlPath;
    }

    public String gameFxmlPath() {
        return this.gameFxmlPath;
    }
}
