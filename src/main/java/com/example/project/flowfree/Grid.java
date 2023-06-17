package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;
import javafx.scene.paint.Color;

public class Grid {
    private Object gridCells[][];

    public Grid() {
        gridCells = LevelDifficulty.EASY.level(0);
    }

    public boolean update(int[] xy) {
        Object o = gridCells[xy[0]][xy[1]];
        if (o instanceof Obstacle) {
            if (((Obstacle) o).destroy()) {
                gridCells[xy[0]][xy[0]] = null;
                return true;
            } else {
                ((Obstacle) gridCells[xy[0]][xy[0]]).destroy();
            }
        }
        return true;
    }

    public boolean update(int[] xy, Color color) {
        Object o = gridCells[xy[0]][xy[1]];
        if (o == null) {
            gridCells[xy[0]][xy[1]] = new Pipe(color);
            return true;
        }
        return false;
    }

    public Object[][] getGridCells() {
        return gridCells;
    }
}
