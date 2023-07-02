package com.example.project.wordle.enums;

import org.junit.jupiter.api.Test;

import static com.example.project.wordle.enums.Status.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class StatusTest {

    @Test
    void testMisses() {
        assertEquals(EMPTY.getColor(), "#FFFFFF");
        assertEquals(INCORRECT.getColor(),"#3A3A3C");
    }

    @Test
    void testHits() {
        assertEquals(PARTIAL.getColor(), "#B59F3B");
        assertEquals(CORRECT.getColor(), "#538D4E");
    }

}
