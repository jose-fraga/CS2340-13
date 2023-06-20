package com.example.project;

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

    private String fileNotFoundMessage(Game game, String pathMethodName, String path) {
        return "FXML File not found for Game." + game + "." + pathMethodName + "() at " + path + "\n" +
                "Check that the file exists in src/main/resources/com/example/project/" + path + "\n" +
                "Check that the file is included in the build.gradle file";
    }

    private void checkFilePaths(Game game) {
        assertNotNull(GameTest.class.getResource(game.initialFxmlPath()), fileNotFoundMessage(game, "initialFxmlPath", game.initialFxmlPath()));
        assertNotNull(GameTest.class.getResource(game.configFxmlPath()), fileNotFoundMessage(game, "configFxmlPath", game.configFxmlPath()));
        assertNotNull(GameTest.class.getResource(game.gameFxmlPath()), fileNotFoundMessage(game, "gameFxmlPath", game.gameFxmlPath()));
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
    void testGameTWENTY_FOURTY_EIGHT() {
        assertEquals("2048", Game.TWENTY_FOURTY_EIGHT.title());

        checkFilePaths(Game.TWENTY_FOURTY_EIGHT);

        assertEquals("twentyfourtyeight/2048Screen.fxml", Game.TWENTY_FOURTY_EIGHT.initialFxmlPath());
        assertEquals("ConfigureScreen.fxml", Game.TWENTY_FOURTY_EIGHT.configFxmlPath());
        assertEquals("twentyfourtyeight/2048GameScreen.fxml", Game.TWENTY_FOURTY_EIGHT.gameFxmlPath());

        assertEquals(Game.TWENTY_FOURTY_EIGHT, Game.values()[2]);
        assertEquals(Game.TWENTY_FOURTY_EIGHT, Game.valueOf("TWENTY_FOURTY_EIGHT"));
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
