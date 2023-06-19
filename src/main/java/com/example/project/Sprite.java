package com.example.project;

public enum Sprite {
    CHARACTER1("Orc ", "images/character1.gif"),
    CHARACTER2("Magician", "images/character2.gif"),
    CHARACTER3("Samurai", "images/character3.gif"),
    CHARACTER4("Scholar", "images/character4.gif");

    private final String label;
    private final String path;

    Sprite(String label, String path) {
        this.label = label;
        this.path = path;
    }

    public String getLabel() { return this.label; }
    public String getPath() { return this.path; }
}
