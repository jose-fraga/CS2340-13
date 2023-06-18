package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;

public class Grid {
    private GridItem gridCells[][];
    private int size;

    public Grid(LevelDifficulty difficulty, int level) {
        this.gridCells = difficulty.level(level);
        this.size = difficulty.size();
    }

    public GridItem[][] getGridCells() {
        return this.gridCells;
    }

    public boolean isComplete() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                GridItem gridItem = this.gridCells[i][j];
                if (gridItem.getIsEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
}
