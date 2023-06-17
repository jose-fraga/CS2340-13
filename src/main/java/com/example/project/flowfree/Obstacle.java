package com.example.project.flowfree;

public class Obstacle extends GridItem {
    private int hitPoints;

    public Obstacle(int hp) {
        this.hitPoints = hp;
    }

    public Obstacle(Obstacle obstacleToCopy, int x, int y) {
        super(x, y);
        this.hitPoints = obstacleToCopy.getHitPoints();
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public boolean destroy() {
        this.hitPoints--;
        return this.hitPoints <= 0;
    }

    @Override
    public String toString() {
        return String.format("Obstacle(%d, %d, hp(%d))", this.getX(), this.getY(), this.hitPoints);
    }

}
