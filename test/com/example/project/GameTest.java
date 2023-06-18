package com.example.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testGameUNSELECTED() {
        assertEquals("CS2340 Project", Game.UNSELECTED.title());
        assertEquals("SelectScreen.fxml", Game.UNSELECTED.initialFxmlPath());
        assertEquals("SelectScreen.fxml", Game.UNSELECTED.gameFxmlPath());
        assertEquals(Game.UNSELECTED, Game.values()[0]);
        assertEquals(Game.UNSELECTED, Game.valueOf("UNSELECTED"));
    }

    @Test
    void testGameFLOW() {
        assertEquals("Flow", Game.FLOW.title());
        assertEquals("FlowScreen.fxml", Game.FLOW.initialFxmlPath());
        assertEquals("FlowGameScreen.fxml", Game.FLOW.gameFxmlPath());
        assertEquals(Game.FLOW, Game.values()[1]);
        assertEquals(Game.FLOW, Game.valueOf("FLOW"));
    }

    @Test
    void testGameTWENTY_FOURTY_EIGHT() {
        assertEquals("2048", Game.TWENTY_FOURTY_EIGHT.title());
        assertEquals("2048Screen.fxml", Game.TWENTY_FOURTY_EIGHT.initialFxmlPath());
        assertEquals("2048GameScreen.fxml", Game.TWENTY_FOURTY_EIGHT.gameFxmlPath());
        assertEquals(Game.TWENTY_FOURTY_EIGHT, Game.values()[2]);
        assertEquals(Game.TWENTY_FOURTY_EIGHT, Game.valueOf("TWENTY_FOURTY_EIGHT"));
    }

    @Test
    void testGameCODENAMES() {
        assertEquals("Codenames", Game.CODENAMES.title());
        assertEquals("codenames/CodenamesSplashScreen.fxml", Game.CODENAMES.initialFxmlPath());
        assertEquals("codenames/CodenamesInitialGamePlayScreen.fxml", Game.CODENAMES.gameFxmlPath());
        assertEquals(Game.CODENAMES, Game.values()[3]);
        assertEquals(Game.CODENAMES, Game.valueOf("CODENAMES"));
    }

    @Test
    void testTitle() {
        assertEquals("CS2340 Project", Game.UNSELECTED.title());
        assertEquals("Flow", Game.FLOW.title());
        assertEquals("2048", Game.TWENTY_FOURTY_EIGHT.title());
        assertEquals("Codenames", Game.CODENAMES.title());
    }

    @Test
    void testInitialFxmlPath() {
        assertEquals("SelectScreen.fxml", Game.UNSELECTED.initialFxmlPath());
        assertEquals("FlowScreen.fxml", Game.FLOW.initialFxmlPath());
        assertEquals("2048Screen.fxml", Game.TWENTY_FOURTY_EIGHT.initialFxmlPath());
        assertEquals("codenames/CodenamesSplashScreen.fxml", Game.CODENAMES.initialFxmlPath());
    }

    @Test
    void testGameFxmlPath() {
        assertEquals("SelectScreen.fxml", Game.UNSELECTED.gameFxmlPath());
        assertEquals("FlowGameScreen.fxml", Game.FLOW.gameFxmlPath());
        assertEquals("2048GameScreen.fxml", Game.TWENTY_FOURTY_EIGHT.gameFxmlPath());
        assertEquals("codenames/CodenamesInitialGamePlayScreen.fxml", Game.CODENAMES.gameFxmlPath());
    }

    @Test
    void testValues() {
        assertEquals(4, Game.values().length);
        assertEquals(Game.UNSELECTED, Game.values()[0]);
        assertEquals(Game.FLOW, Game.values()[1]);
        assertEquals(Game.TWENTY_FOURTY_EIGHT, Game.values()[2]);
        assertEquals(Game.CODENAMES, Game.values()[3]);
    }

    @Test
    void testValueOf() {
        assertEquals(Game.UNSELECTED, Game.valueOf("UNSELECTED"));
        assertEquals(Game.FLOW, Game.valueOf("FLOW"));
        assertEquals(Game.TWENTY_FOURTY_EIGHT, Game.valueOf("TWENTY_FOURTY_EIGHT"));
        assertEquals(Game.CODENAMES, Game.valueOf("CODENAMES"));
    }

    @Test
    void testGameToString() {
        assertEquals("UNSELECTED", Game.UNSELECTED.toString());
        assertEquals("FLOW", Game.FLOW.toString());
        assertEquals("TWENTY_FOURTY_EIGHT", Game.TWENTY_FOURTY_EIGHT.toString());
        assertEquals("CODENAMES", Game.CODENAMES.toString());
    }

    @Test
    void testGameEquals() {
        assertNotEquals(Game.UNSELECTED, Game.FLOW);
        assertNotEquals(Game.UNSELECTED, Game.TWENTY_FOURTY_EIGHT);
        assertNotEquals(Game.UNSELECTED, Game.CODENAMES);

        assertNotEquals(Game.FLOW, Game.TWENTY_FOURTY_EIGHT);
        assertNotEquals(Game.FLOW, Game.CODENAMES);

        assertNotEquals(Game.TWENTY_FOURTY_EIGHT, Game.CODENAMES);
    }


}