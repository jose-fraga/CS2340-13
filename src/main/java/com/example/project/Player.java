package com.example.project;

public class Player {
    private String name = DEFAULT_NAME;
    private String spritePath = DEFAULT_SPRITE_PATH;
    private static String DEFAULT_NAME = "Player";
    private static String DEFAULT_SPRITE_PATH = Sprite.CHARACTER1.getPath();

    public Player() {}

    public Player(String name) {
        this.name = name;
    }

    public String getName() { return this.name; }
    public void setName(String name) { this.name = name.isBlank() ? DEFAULT_NAME : name; }

    public String getSpritePath() {
        return this.spritePath;
    }
    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath.isBlank() ? DEFAULT_SPRITE_PATH : spritePath;
    }
}
