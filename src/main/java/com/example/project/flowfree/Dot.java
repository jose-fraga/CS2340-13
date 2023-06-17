package com.example.project.flowfree;
import javafx.scene.paint.Color;

public class Dot {
    private Color color;
    private boolean isConnected;

    public Dot(Color color) {
        this.color = color;
    }

    public Dot(Dot dotToCopy) {
        this.color = dotToCopy.getColor();
    }

    public Color getColor() {
        return this.color;
    }
}
