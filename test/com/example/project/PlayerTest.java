package com.example.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTest {

    @Test
    void testNameNotNull() {
        Player player = new Player();
        assertNotNull(player.getName());
    }

    @Test
    void testSpritePathNotNull() {
        Player player = new Player();
        assertNotNull(player.getSpritePath());
    }

    @Test
    void testNameEqualsExpectedName() {
        Player player = new Player("John");
        assertEquals("John", player.getName());
    }

    @Test
    void testSpritePathEqualsExpectedSpritePath() {
        Player player = new Player();
        player.setSpritePath("custom_sprite.png");
        assertEquals("custom_sprite.png", player.getSpritePath());
    }
}