package com.example.project.codenames;

import com.example.project.codenames.enums.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    @Test
    void getNumOfCards() {
        Team team = new Team(Type.RED, 9);
        assertEquals(team.getNumOfCards(), 9);
    }

    @Test
    void decrementCardCount() {
        Team team = new Team(Type.RED, 9);
        assertEquals(team.getNumOfCards(), 9);
        team.decrementCardCount();
        assertEquals(team.getNumOfCards(), 8);
    }

    @Test
    void getType() {
        Team team = new Team(Type.RED, 9);
        assertEquals(team.getType(), Type.RED);
    }

    @Test
    void hasWon() {
        Team team = new Team(Type.RED, 1);
        assertFalse(team.hasWon());
        team.decrementCardCount();
        assertTrue(team.hasWon());
    }
}