package com.example.project.codenames;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoundTest {
    private Round round;

    @BeforeEach
    public void setUp() {
        DictionaryService.populate(); // Populate gameWords list
        round = new Round();
    }

    @Test
    public void testGetTeam1() {
        Team team1 = round.getTeam1();
        assertNotNull(team1);
        assertEquals(9, team1.getNumOfCards());
    }

    @Test
    public void testGetTeam2() {
        Team team2 = round.getTeam2();
        assertNotNull(team2);
        assertEquals(8, team2.getNumOfCards());
    }

    @Test
    public void testGetWords() {
        assertNotNull(round.getWords());
        assertEquals(25, round.getWords().size());
    }

}
