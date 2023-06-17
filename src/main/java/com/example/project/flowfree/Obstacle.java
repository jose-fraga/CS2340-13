package com.example.project.flowfree;

public class Obstacle {
    private int hitPoints;

    public Obstacle(int hp) {
        this.hitPoints = hp;
    }

    public Obstacle(Obstacle obstacleToCopy) {
        this.hitPoints = obstacleToCopy.getHitPoints();
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public boolean destroy() {
        this.hitPoints--;
        return this.hitPoints <= 0;
    }

}
