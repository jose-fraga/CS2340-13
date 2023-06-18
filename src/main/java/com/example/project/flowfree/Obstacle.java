package com.example.project.flowfree;

import javafx.scene.paint.Color;

public class Obstacle extends Pipe {
    private int hitPoints;

    public Obstacle(int hp) {
        super();
        this.hitPoints = hp;
    }

    public Obstacle(Obstacle obstacleToCopy, int x, int y) {
        super(x, y);
        this.hitPoints = obstacleToCopy.getHitPoints();
        this.setIsEmpty(false);
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public boolean destroy() {
        this.hitPoints--;
        if (this.hitPoints <= 0) {
            this.setIsEmpty(true);
        }
        return this.isCleared();
    }

    public boolean isCleared() {
        return this.hitPoints <= 0;
    }

    @Override
    public String toString() {
        return String.format("Obstacle(%d, %d, hp(%d))", this.getX(), this.getY(), this.hitPoints);
    }

}
