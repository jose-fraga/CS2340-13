package com.example.project.wordle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryServiceTest {
    @Test
    void checkValidityNullWordReturnsFalse() {
        String word = "";
        boolean isValid = DictionaryService.checkValidity(word);
        assertFalse(isValid);
    }

    @Test
    void generateWordNegativeLengthReturnsNull() {
        int length = -5;
        String generatedWord = null;
        if (length >= 0) {
            generatedWord = DictionaryService.generateWord(length);
        }
        assertNull(generatedWord);
    }

    @Test
    void checkENV() {
        assertNotNull(System.getenv("WRL_RANDOM_WORD_API"));
        assertNotNull(System.getenv("DICTIONARY_API"));
    }

    @Test
    void checkValidityOfWords() {
        String[] words = {"apple", "", "abcde", "mapple", "amazing"};
        assertTrue(DictionaryService.checkValidity(words[0]));
        assertFalse(DictionaryService.checkValidity(words[1]));
        assertFalse(DictionaryService.checkValidity(words[2]));
        assertFalse(DictionaryService.checkValidity(words[3]));
        assertTrue(DictionaryService.checkValidity(words[4]));
    }

    @Test
    void checkMatchingLength() {
        assertNotNull(DictionaryService.generateWord(3));
        assertEquals(DictionaryService.generateWord(6).length(), 6);
        assertEquals(DictionaryService.generateWord(9).length(), 9);
    }
}
