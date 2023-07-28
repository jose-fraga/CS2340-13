package com.example.project;

public enum Game {
    UNSELECTED("CS2340 Project",
            "CRSelectScreen.fxml",
            "CRConfigureScreen.fxml",
            "CRSelectScreen.fxml"),
    FLOW("Flow Free",
            "flowfree/FFSplashScreen.fxml",
            "CRConfigureScreen.fxml",
            "flowfree/FFLevelScreen.fxml"),
    WORDLE("Wordle",
            "wordle/WRLSplashScreen.fxml",
            "CRConfigureScreen.fxml",
            "wordle/WRLLevelScreen.fxml"),
    CODENAMES("Codenames",
            "codenames/CNSplashScreen.fxml",
            "codenames/CNConfigScreen.fxml",
            "codenames/CNGameScreen.fxml");

    private final String title;
    private final String initialFxmlPath;
    private final String configFxmlPath;
    private final String gameFxmlPath;

    Game(String title, String initialFxmlPath, String configFxmlPath, String gameFxmlPath) {
        this.title = title;
        this.initialFxmlPath = initialFxmlPath;
        this.configFxmlPath = configFxmlPath;
        this.gameFxmlPath = gameFxmlPath;
    }

    public String title() { return this.title; }
    public String initialFxmlPath() { return this.initialFxmlPath; }
    public String configFxmlPath() { return this.configFxmlPath; }
    public String gameFxmlPath() { return this.gameFxmlPath; }
}
