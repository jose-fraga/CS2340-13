package com.example.project;

import org.junit.jupiter.api.Test;
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
    void testNameNotNullWithCustomName() {
        Player player = new Player("John");
        assertNotNull(player.getName());
    }

    @Test
    void testSpritePathNotNullWithCustomSpritePath() {
        Player player = new Player();
        player.setSpritePath("custom_sprite.png");
        assertNotNull(player.getSpritePath());
    }
}
