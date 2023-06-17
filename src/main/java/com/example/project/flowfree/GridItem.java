package com.example.project.flowfree;

public class GridItem {
    private int x;
    private int y;
    private boolean isEmpty;

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isEmpty() { return isEmpty; }
    public void setIsEmpty(boolean isEmpty) { this.isEmpty = isEmpty;}

    public GridItem() {}

    public GridItem(int x, int y) {
        this.x = x;
        this.y = y;
        this.isEmpty = false;
    }

    public GridItem(int x, int y, boolean isEmpty) {
        this.x = x;
        this.y = y;
        this.isEmpty = isEmpty;
    }

    @Override
    public String toString() {
        return String.format("GridItem(%d, %d)", this.x, this.y);
    }
}
