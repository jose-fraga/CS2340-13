package com.example.project;

public enum Game {
    UNSELECTED("CS2340 Project",
            "SelectScreen.fxml",
            "ConfigureScreen.fxml",
            "SelectScreen.fxml"),
    FLOW("Flow",
            "flowfree/FlowfreeSplashScreen.fxml",
            "flowfree/FlowfreeConfigurationScreen.fxml",
            "flowfree/FlowfreeInitialGamePlayScreen.fxml"),
    TWENTY_FOURTY_EIGHT("2048",
            "2048Screen.fxml",
            "ConfigureScreen.fxml",
            "2048GameScreen.fxml"),
    CODENAMES("Codenames",
            "codenames/CodenamesSplashScreen.fxml",
            "codenames/CodenamesConfigurationScreen.fxml",
            "codenames/CodenamesInitialGamePlayScreen.fxml");

    private final String title;
    private final String initialFxmlPath;
    private final String gameFxmlPath;

    Game(String title, String initialFxmlPath, String configFxmlPath, String gameFxmlPath) {
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
