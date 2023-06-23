package com.example.project.flowfree;

public class GridItem {
    private int x;
    private int y;
    private boolean isEmpty;

    public GridItem() {}
    public GridItem(int x, int y) {
        this.x = x;
        this.y = y;
        this.isEmpty = false;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }

    public boolean getIsEmpty() { return this.isEmpty; }
    public void setIsEmpty(boolean isEmpty) { this.isEmpty = isEmpty;}

    @Override
    public String toString() {
        return String.format("GridItem(%d, %d)", this.x, this.y);
    }
}
