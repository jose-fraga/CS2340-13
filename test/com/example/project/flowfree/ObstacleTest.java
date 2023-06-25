package com.example.project.flowfree;

import com.example.project.flowfree.Obstacle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ObstacleTest {
    @Test
    void testDestroyObstacle() {
        int health = 15;
        Obstacle o1 = new Obstacle(health);
        o1.destroy();
        assertFalse(o1.getHitPoints() == health, "Destroy is not working");
    }
    @Test
    void testObstacleCreated() {
        Obstacle o1 = new Obstacle(15);
        assertNotNull(o1, "Obstacle is null");
    }
}
