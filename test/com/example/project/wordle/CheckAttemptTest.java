package com.example.project.wordle;

import com.example.project.wordle.enums.Status;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckAttemptTest {
    @Test
    void testAttemptedLetterConstructor() {
        int index = 1;
        AttemptedLetter al = new AttemptedLetter(index);
        assertNotNull(al);
    }

    @Test
    void testDefaultStatus() {
        AttemptedLetter al = new AttemptedLetter(1);
        assertEquals(al.getStatus(),Status.EMPTY);
    }
}
