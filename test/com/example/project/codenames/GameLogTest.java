package com.example.project.codenames;

import com.example.project.codenames.enums.Type;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameLogTest {
    @Test
    public void testGetLogItems() {
        GameLog log = new GameLog();
        log.addLogItem(new Word(Type.RED, "test", "..."), "red");
        assertNotNull(log.getLogItems());
        assertEquals(1, log.getLogItems().size());
    }

    @Test
    public void testAddLogItem() {
        GameLog log = new GameLog();
        log.addLogItem(new Word(Type.RED, "test", "..."), "red");
        assertInstanceOf(TextFlow.class, log.getLogItems().get(0));
    }
}
