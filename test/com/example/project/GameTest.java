package com.example.project;

import com.example.project.EnumPathTestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GameTest {
    /*
    guide: https://www.vogella.com/tutorials/JUnit4/article.html
    source: https://learning.oreilly.com/library/view/effective-unit-testing/9781935182573/OEBPS/Text/kindle_split_021_split_002.html
        JUnit works with assertions to verify that the code under test behaves as expected. The following are the most commonly used assertions:
        assertEquals— Asserts that two objects (or primitives) are equal.
        assertArrayEquals— Asserts that two arrays have the same items.
        assertTrue— Asserts that a statement is true.
        assertFalse— Asserts that a statement is false.
        assertNull— Asserts that an object reference is null.
        assertNotNull— Asserts that an object reference is not null.
        assertSame— Asserts that two object references point to the same instance.
        assertNotSame— Asserts that two object references do not point to the same instance.
        assertThat— Asserts that an object matches the given conditions (see section A.2.2 for a more thorough explanation).
     */

    private EnumPathTestHelper<Game> helper = new EnumPathTestHelper<Game>();

    private void checkFilePaths(Game game) {
        helper.assertPathResolves(game.initialFxmlPath(), "initialFxmlPath");
        helper.assertPathResolves(game.configFxmlPath(), "configFxmlPath");
        helper.assertPathResolves(game.gameFxmlPath(), "gameFxmlPath");
    }

    @Test
    void testGameUNSELECTED() {
        assertEquals("CS2340 Project", Game.UNSELECTED.title());

        checkFilePaths(Game.UNSELECTED);

        assertEquals("SelectScreen.fxml", Game.UNSELECTED.initialFxmlPath());
        assertEquals("ConfigureScreen.fxml", Game.UNSELECTED.configFxmlPath());
        assertEquals("SelectScreen.fxml", Game.UNSELECTED.gameFxmlPath());

        assertEquals(Game.UNSELECTED, Game.values()[0]);
        assertEquals(Game.UNSELECTED, Game.valueOf("UNSELECTED"));
    }

    @Test
    void testGameFLOW() {
        assertEquals("Flow Free", Game.FLOW.title());

        checkFilePaths(Game.FLOW);

        assertEquals("flowfree/FFSplashScreen.fxml", Game.FLOW.initialFxmlPath());
        assertEquals("ConfigureScreen.fxml", Game.FLOW.configFxmlPath());
        assertEquals("flowfree/FFLevelScreen.fxml", Game.FLOW.gameFxmlPath());

        assertEquals(Game.FLOW, Game.values()[1]);
        assertEquals(Game.FLOW, Game.valueOf("FLOW"));
    }

    @Test
    void testGameWORDLE() {
        assertEquals("Wordle", Game.WORDLE.title());

        checkFilePaths(Game.WORDLE);

        assertEquals("wordle/WRLSplashScreen.fxml", Game.WORDLE.initialFxmlPath());
        assertEquals("ConfigureScreen.fxml", Game.WORDLE.configFxmlPath());
        assertEquals("wordle/WRLLevelScreen.fxml", Game.WORDLE.gameFxmlPath());

        assertEquals(Game.WORDLE, Game.values()[2]);
        assertEquals(Game.WORDLE, Game.valueOf("WORDLE"));
    }

    @Test
    void testGameCODENAMES() {
        assertEquals("Codenames", Game.CODENAMES.title());

        checkFilePaths(Game.CODENAMES);

        assertEquals("codenames/CodenamesSplashScreen.fxml", Game.CODENAMES.initialFxmlPath());
        assertEquals("ConfigureScreen.fxml", Game.CODENAMES.configFxmlPath());
        assertEquals("codenames/CodenamesInitialGamePlayScreen.fxml", Game.CODENAMES.gameFxmlPath());

        assertEquals(Game.CODENAMES, Game.values()[3]);
        assertEquals(Game.CODENAMES, Game.valueOf("CODENAMES"));
    }
}
