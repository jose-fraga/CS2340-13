package com.example.project.flowfree;

import com.example.project.flowfree.enums.LevelDifficulty;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    @Test
    void checkNullLevel() {
        Level level = new Level(0);
        level.setGrid(LevelDifficulty.EASY);
        assertNotNull(level);
        assertNotNull(level.getTimer());
        assertNotNull(level.getGrid());
    }

    @Test
     void gridTests() {
        gridCompare(0);
        gridCompare(1);
        gridCompare(2);
    }

    void gridCompare(int number) {
        Level level = new Level(number);
        level.setGrid(LevelDifficulty.EASY);
        GridItem[][] curr = level.getGrid().getGridCells();
        GridItem[][] base = LevelDifficulty.EASY.level(number);
        for (int i = 0; i < curr.length; i++) {
            for (int j = 0; j < curr.length; j++) {
                assertEquals(curr[i][j].getX(), base[i][j].getX());
                assertEquals(curr[i][j].getY(), base[i][j].getY());
                assertEquals(curr[i][j].getIsEmpty(), base[i][j].getIsEmpty());
            }
        }
    }

    @Test
    void timerTest() {
        Level level = new Level(1);
        level.setGrid(LevelDifficulty.EASY);
        level.pause();
        assertTrue(level.isPaused());
        level.resume();
        assertFalse(level.isPaused());
        level.restart();
        assertTrue(level.getTimer().isStarted());
        level.complete();
        assertTrue(level.getTimer().isStopped());
    }

    @Test
    void timerLimitTest() {
        Level level = new Level(1);
        assertTrue(level.getTimer().isStarted());
        if (level.getSecondsLeft() == 0) {
            assertTrue(level.getTimer().isStopped());
        }
    }
}
