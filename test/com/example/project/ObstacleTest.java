package com.example.project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.project.flowfree.Obstacle;
import org.junit.jupiter.api.Test;

public class ObstacleTest {
    @Test
    void testDestroyObstacle() {
        int health = 15;
        Obstacle o1 = new Obstacle(health);
        o1.destroy();
        assertTrue(o1.getHitPoints() == health, "Destroy is not working");
    }
    @Test
    void testObstacleCreated() {
        Obstacle o1 = new Obstacle(15);
        assertNull(o1, "Obstacle is null");
    }
}
