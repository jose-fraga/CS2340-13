package com.example.project.flowfree;

import javafx.scene.paint.Color;

public class Dot extends ColoredGridItem {
    // Might remove isConnected
    private boolean isConnected;

    public Dot(Color color) { super(color); }
    public Dot(Dot dotToCopy, int x, int y) { super(dotToCopy.getColor(), x,y); }

    public void setConnected(boolean value) { this.isConnected = value; }

    public boolean isConnectingDot(GridItem gridItem) {
        boolean result = false;
        if (gridItem instanceof Dot && gridItem != this) {
            Dot otherDot = (Dot) gridItem;
            result = this.getColor().equals(otherDot.getColor());
        }
        return result;
    }

    @Override
    public String toString() {
        return String.format("Dot(%d, %d, %s)", this.getX(), this.getY(), this.getHexColor());
    }
}
