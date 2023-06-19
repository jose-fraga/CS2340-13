package com.example.project.flowfree.enums;

import com.example.project.flowfree.Dot;
import com.example.project.flowfree.GridItem;
import com.example.project.flowfree.Obstacle;
import com.example.project.flowfree.Pipe;
import javafx.scene.paint.Color;

public enum LevelDifficulty {
    EASY("flowfree/levels/Easy.fxml", 4, new GridItem[][]{
            {
                    new Dot(Color.GREEN), new Dot(Color.RED), new Dot(Color.BLUE), new Dot(Color.RED),
                    null, null, new Obstacle(1), null,
                    null, null, new Dot(Color.BLUE), null,
                    new Dot(Color.GREEN), null, new Obstacle(10), null
            },
            {
                    new Dot(Color.ORCHID), new Obstacle(5), new Obstacle(5), new Obstacle(5),
                    new Dot(Color.LIMEGREEN), new Obstacle(10), new Dot(Color.LIMEGREEN), new Obstacle(5),
                    new Dot(Color.MEDIUMPURPLE), new Obstacle(10), new Dot(Color.MEDIUMPURPLE), new Obstacle(5),
                    new Dot(Color.ORCHID), new Obstacle(5), new Obstacle(5), new Obstacle(5)
            },
            {
                    new Dot(Color.LIMEGREEN), null, null, new Dot(Color.RED),
                    new Dot(Color.BLUEVIOLET), new Obstacle(15), new Dot(Color.RED), new Dot(Color.BLUEVIOLET),
                    null, null, new Dot(Color.LIMEGREEN), new Obstacle(15),
                    null, new Obstacle(10), null, null
            },
    });

    private final String path;
    private final int size;
    private final GridItem[][] levels;

    LevelDifficulty(String path, int size, GridItem[][] levels) {
        this.path = path;
        this.size = size;
        this.levels = levels;
    }

    public String getPath() { return this.path; }
    public int getSize() { return this.size; }

    public GridItem[][] level(int level) {
        GridItem[][] copiedLevel = new GridItem[size][size];
        if (level >= 0 && level < levels.length) {
            GridItem[] levelToCopy = levels[level];
            for (int i = 0; i < levelToCopy.length; i++) {
                int y = i % size;
                int x = i / size;
                GridItem itemToCopy = levelToCopy[i];
                GridItem copiedItem;
                if (itemToCopy instanceof Obstacle) {
                    copiedItem = new Obstacle((Obstacle) itemToCopy, x, y);
                } else if (itemToCopy instanceof Dot) {
                    copiedItem = new Dot((Dot) itemToCopy, x, y);
                } else {
                    copiedItem = new Pipe(x, y);
                }
                copiedLevel[x][y] = copiedItem;
            }
        }
        return copiedLevel;
    }
}
