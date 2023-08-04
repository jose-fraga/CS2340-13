package com.example.project.codenames;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLogTest {
    @Test
    public void testGetLogItems() {
        GameLog log = new GameLog();
//        log.addLogItem("test");
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("test");

        assertEquals(expected, log.getLogItems());
    }

    @Test
    public void testAddLogItem() {
        GameLog log = new GameLog();
//        log.addLogItem("test");
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("test");

        assertEquals(expected, log.getLogItems());
    }
}
