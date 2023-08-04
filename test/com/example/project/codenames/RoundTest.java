package com.example.project.codenames;

import com.example.project.codenames.enums.Role;
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
    public void testGetActiveTeam() {
        Team activeTeam = round.activeTeam();
        assertNotNull(activeTeam);
        assertEquals(9, activeTeam.getNumOfCards());
        assertEquals(Role.ACTIVE, activeTeam.getRoleType());
    }

    @Test
    public void testGetPassiveTeam() {
        Team passiveTeam = round.passiveTeam();
        assertNotNull(passiveTeam);
        assertEquals(8, passiveTeam.getNumOfCards());
        assertEquals(Role.PASSIVE, passiveTeam.getRoleType());
    }

    @Test
    public void testGetWords() {
        assertNotNull(round.getWords());
        assertEquals(25, round.getWords().size());
    }
}
