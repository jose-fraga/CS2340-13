package com.example.project;

import org.junit.jupiter.api.Test;
import com.example.project.codenames.GameLog;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


public class GameLogTest {
    @Test
    public void testGetLogItems() {
        GameLog log = new GameLog();
        log.addLogItem("test");
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("test");
        assertEquals(expected, log.getLogItems());
    }
    @Test
    public void testAddLogItem() {
        GameLog log = new GameLog();
        log.addLogItem("test");
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("test");
        assertEquals(expected, log.getLogItems());
    }
}
