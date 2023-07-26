package com.example.project.codenames;

import com.example.project.codenames.enums.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WordTest{
    private Word word;

    @BeforeEach
    public void setUp() {
        word = new Word(Type.ASSASSIN, "word", "definition");
    }

    @Test
    public void testGetWord() {
        assertEquals("word", word.getWord());
    }

    @Test
    public void testGetDefinition() {
        assertEquals("definition", word.getDefinition());
    }

    @Test
    public void testGetIsSelected() {
        assertFalse(word.getIsSelected());
    }

    @Test
    public void testSelect() {
        word.select();
        assertTrue(word.getIsSelected());
    }

    @Test
    public void testGetType() {
        assertEquals(Type.ASSASSIN, word.getType());
    }

    @Test
    public void testSetType() {
        word.setType(Type.RED);
        assertEquals(Type.RED, word.getType());
    }

    @Test
    public void testToString() {
        assertNotNull(word.toString());
    }
}
