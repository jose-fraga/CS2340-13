package com.example.project.wordle.enums;

import com.example.project.EnumPathTestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordDifficultyTest {
    private EnumPathTestHelper<WordDifficulty> helper = new EnumPathTestHelper<WordDifficulty>();
    @Test
    void testFOUR() {
        assertEquals("wordle/levels/Four.fxml", WordDifficulty.FOUR.getPath());
        helper.assertPathResolves(WordDifficulty.FOUR.getPath(), "getPath");
    }

    @Test
    void testFIVE() {
        assertEquals("wordle/levels/Five.fxml", WordDifficulty.FIVE.getPath());
        helper.assertPathResolves(WordDifficulty.FIVE.getPath(), "getPath");

    }

    @Test
    void testSIX() {
        assertEquals("wordle/levels/Six.fxml", WordDifficulty.SIX.getPath());
        helper.assertPathResolves(WordDifficulty.SIX.getPath(), "getPath");
    }

    @Test
    void testPathValuesAreExpected() {
        assertEquals("wordle/levels/Four.fxml", WordDifficulty.FOUR.getPath());
        assertEquals("wordle/levels/Five.fxml", WordDifficulty.FIVE.getPath());
        assertEquals("wordle/levels/Six.fxml", WordDifficulty.SIX.getPath());
    }

    @Test
    void testPathsResolve() {
        helper.assertPathResolves(WordDifficulty.FOUR.getPath(), "getPath");
        helper.assertPathResolves(WordDifficulty.FIVE.getPath(), "getPath");
        helper.assertPathResolves(WordDifficulty.SIX.getPath(), "getPath");

    }
}