package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;

public class Grid {
    private final GridItem[][] gridCells;
    private final int size;
    private boolean isComplete;

    public Grid(LevelDifficulty difficulty, int level) {
        this.gridCells = difficulty.level(level);
        this.size = difficulty.getSize();
        this.isComplete = false;
    }

    public GridItem[][] getGridCells() { return this.gridCells; }

    public boolean isComplete() {
        if (this.isComplete) return true;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                GridItem gridItem = this.gridCells[i][j];
                if (gridItem.getIsEmpty()) {
                    return false;
                }
            }
        }
        this.isComplete = true;
        return true;
    }
}
