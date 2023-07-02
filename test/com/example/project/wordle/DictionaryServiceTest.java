package com.example.project.wordle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryServiceTest {
    @Test
    void checkValidityNullWordReturnsFalse() {
        String word = null;
        boolean isValid = DictionaryService.checkValidity(word);
        assertFalse(isValid);
    }

    @Test
    void generateWordNegativeLengthReturnsNull() {
        int length = -5;
        String generatedWord = DictionaryService.generateWord(length);
        assertNull(generatedWord);
    }

    @Test
    void checkValidityEnv() {
        assertNotNull(System.getenv("NINJA_API_URL"));
        assertNotNull(System.getenv("NINJA_API_KEY"));
    }

    @Test
    void checkValidityOfWords() {
        String[] words = {"apple", "ap", "abcde", "mapple", "amazing"};
        assertTrue(DictionaryService.checkValidity(words[0]));
        assertFalse(DictionaryService.checkValidity(words[1]));
        assertFalse(DictionaryService.checkValidity(words[2]));
        assertFalse(DictionaryService.checkValidity(words[3]));
        assertTrue(DictionaryService.checkValidity(words[4]));
    }
}