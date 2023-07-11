package com.example.project.codenames;

import com.example.project.Player;

public class CNConfigSingleton {
    private static CNConfigSingleton config_instance = null;
    private Player team1;
    private Player team2;

    private CNConfigSingleton() {
        this.team1 = new Player("Team 1");
        this.team2 = new Player("Team 2");
    }
    public static synchronized CNConfigSingleton getInstance()
    {
        if (config_instance == null)
            config_instance = new CNConfigSingleton();

        return config_instance;
    }

    public Player getTeam1() {
        return team1;
    }

    public Player getTeam2() {
        return team2;
    }
}
