package com.example.project.codenames;

import com.example.project.codenames.enums.Role;
import com.example.project.codenames.enums.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    @Test
    void getNumOfCards() {
        Team team = new Team(Type.RED, Role.ACTIVE);
        assertEquals(team.getNumOfCards(), 9);
    }

    @Test
    void decrementCardCount() {
        Team team = new Team(Type.RED, Role.ACTIVE);
        assertEquals(team.getNumOfCards(), 9);
        team.decrementCardCount();
        assertEquals(team.getNumOfCards(), 8);
    }

    @Test
    void getType() {
        Team team = new Team(Type.RED, Role.ACTIVE);
        assertEquals(team.getType(), Type.RED);
    }

    @Test
    void hasWon() {
        Team team = new Team(Type.RED, Role.ACTIVE);
        assertFalse(team.hasWon());
        for (int i = 0; i < 9; i++) {
            team.decrementCardCount();
        }
        assertTrue(team.hasWon());
    }
}
