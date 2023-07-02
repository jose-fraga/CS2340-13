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
}