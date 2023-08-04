package com.example.project.wordle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LifeTest {
    @Test
    void testGetLives() {
        Life life = new Life();
        assertEquals(6, life.getLives());
    }

    @Test
    void testCalculateLives() {
        Life life = new Life();
        life.calculateLives("hello", "hello");
        assertEquals(6, life.getLives());
        life.calculateLives("hello", "jello");
        assertEquals(5, life.getLives());
    }
}
