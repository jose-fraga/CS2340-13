package com.example.project.codenames;

import org.junit.jupiter.api.Test;

import static com.example.project.codenames.enums.Type.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeTest {
    @Test
    void testPlayer() {
        assertEquals(RED.getColor(), "#CF2129");
        assertEquals(BLUE.getColor(),"#03B8D0");
    }

    @Test
    void testSpecial() {
        assertEquals(NEUTRAL.getColor(), "#E6CCA2");
        assertEquals(ASSASSIN.getColor(), "#373737");
    }
}
