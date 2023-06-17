package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;
import javafx.scene.paint.Color;

public class Grid {
    private GridItem gridCells[][];

    public Grid() {
        gridCells = LevelDifficulty.EASY.level(0);
    }

    public boolean update(int[] xy) {
        GridItem o = gridCells[xy[0]][xy[1]];
        if (o instanceof Obstacle) {
            if (((Obstacle) o).destroy()) {
                gridCells[xy[0]][xy[1]] = null;
                return true;
            }
        }
        return false;
    }

    public boolean update(int[] xy, Color color) {
        GridItem o = gridCells[xy[0]][xy[1]];
        if (o == null) {
            gridCells[xy[0]][xy[1]] = new Pipe(color,xy[0],xy[1]);
            return true;
        }
        return false;
    }

    public GridItem[][] getGridCells() {
        return gridCells;
    }
}
