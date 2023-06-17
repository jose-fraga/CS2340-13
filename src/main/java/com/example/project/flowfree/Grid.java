package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;

import java.util.Arrays;

public class Grid {
    private Object gridCells[][];

    public Grid() {
        gridCells = LevelDifficulty.EASY.level(2);
    }

    public boolean update(int x, int y) {
        return true;
    }
}
