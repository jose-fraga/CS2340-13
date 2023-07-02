package com.exmaple.project.wordle;

import com.example.project.wordle.Life;
import org.junit.jupiter.api.Test;

import static com.example.project.wordle.Life.*;
import static org.junit.jupiter.api.Assertions.*;
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
