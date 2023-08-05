package com.example.project;

public enum Sprite {
    CHARACTER1("images/character1.gif"),
    CHARACTER2("images/character2.gif"),
    CHARACTER3("images/character3.gif"),
    CHARACTER4("images/character4.gif");

    private final String path;

    Sprite(String path) {
        this.path = path;
    }

    public static String[] getCharacters() {
        return new String[] {
                Sprite.CHARACTER1.name(),
                Sprite.CHARACTER2.name(),
                Sprite.CHARACTER3.name(),
                Sprite.CHARACTER4.name()
        };
    }

    public String getPath() { return this.path; }
}
