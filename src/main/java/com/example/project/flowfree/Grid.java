package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;
import javafx.scene.paint.Color;

public class Grid {
    private GridItem gridCells[][];
    private int size;

    public Grid(LevelDifficulty difficulty, int level) {
        gridCells = difficulty.level(level);
        size = difficulty.size();
    }

    public boolean isComplete() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GridItem gridItem = gridCells[i][j];
                if (gridItem.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public GridItem[][] getGridCells() {
        return gridCells;
    }
}
