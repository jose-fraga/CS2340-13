package com.example.project.flowfree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObstacleTest {
    @Test
    void testDestroyObstacle() {
        int health = 15;
        Obstacle o1 = new Obstacle(health);
        o1.destroy();
        assertNotEquals(o1.getHitPoints(), health, "Destroy is not working");
    }

    @Test
    void testObstacleCreated() {
        Obstacle o1 = new Obstacle(15);
        assertNotNull(o1, "Obstacle is null");
    }
}
