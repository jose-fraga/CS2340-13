package com.example.project.codenames;

import java.util.ArrayList;

public class GameLog {
    private ArrayList<String> logItems;

    public ArrayList<String> getLogItems() { return this.logItems; }
    public void addLogItem(String logItem) { this.logItems.add(logItem); }
}
