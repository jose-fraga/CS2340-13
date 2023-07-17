package com.example.project.wordle.controllers;

import com.example.project.wordle.Life;

public class WRLSingleton {
    private static WRLSingleton instance;
    private Life life;

    private WRLSingleton() {
        life = new Life();
    }

    public static synchronized WRLSingleton getInstance() {
        if (instance == null) {
            instance = new WRLSingleton();
        }
        return instance;
    }

    public Life getLife() {
        return life;
    }
}