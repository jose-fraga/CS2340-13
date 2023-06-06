package com.example.project.flowfree;

import com.example.project.Player;

public class FlowfreeConfigSingleton {
    private static FlowfreeConfigSingleton config_instance = null;
    private Player player1;

    private FlowfreeConfigSingleton() {
        this.player1 = new Player("PLayer 1");
    }
    public static synchronized FlowfreeConfigSingleton getInstance()
    {
        if (config_instance == null)
            config_instance = new FlowfreeConfigSingleton();

        return config_instance;
    }

    public Player getPlayer1() {
        return player1;
    }
}
