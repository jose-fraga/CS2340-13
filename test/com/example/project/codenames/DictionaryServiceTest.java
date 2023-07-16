package com.example.project.codenames;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryServiceTest {
    @Test
    void checkENV() {
        assertNotNull(System.getenv("CN_RANDOM_WORD_API"));
        assertNotNull(System.getenv("DEFINITION_API"));
    }

    @Test
    void checkPopulate() {
        assertEquals(DictionaryService.getGameWords().size(), 0);
        DictionaryService.populate();
        assertEquals(DictionaryService.getGameWords().size(), 25);
    }

    @Test
    void checkGenerateWord() {
//        for (int i = 0; i < 15; i++) {
//            assertNotNull(DictionaryService.generateWord());
//        }
    }

    @Test
    void checkObtainDefinition() {
//        String[] testWords = {
//                "Apple", "Balloon", "Camel", "Donkey", "Eagle",
//                "Floor", "Google", "Hot", "Igloo", "Jungle",
//                "Key", "Land", "Mouse", "North", "Otter"
//        };
//
//        for (int i = 0; i < 15; i++) {
//            assertNotNull(DictionaryService.obtainDefinition(testWords[i]));
//        }
    }
}