package com.example.project.flowfree.enums;

import com.example.project.flowfree.Dot;
import com.example.project.flowfree.GridItem;
import com.example.project.flowfree.Obstacle;
import com.example.project.flowfree.Pipe;
import javafx.scene.paint.Color;

public enum LevelDifficulty {
    EASY("flowfree/levels/Easy.fxml", 4, 30, new GridItem[][]{
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
    }),
    MEDIUM("flowfree/levels/Medium.fxml", 5, 40, new GridItem[][]{
            {
                    new Dot(Color.ORANGE), new Dot(Color.RED), null, new Obstacle(10), null,
                    new Obstacle(5), new Dot(Color.PURPLE), null, null, new Dot(Color.RED),
                    null, new Obstacle(5), new Dot(Color.ORANGE), null, new Dot(Color.PURPLE),
                    null, new Obstacle(2), new Obstacle(5), new Dot(Color.BLUE), new Dot(Color.GREEN),
                    new Dot(Color.BLUE), new Dot(Color.GREEN), null, null, new Obstacle(10)
            },
            {
                    new Dot(Color.BLUE), new Dot(Color.PINK), new Obstacle(10), new Dot(Color.PINK), new Dot(Color.RED),
                    null, new Dot(Color.YELLOW), new Obstacle(5), new Obstacle(5), null,
                    new Obstacle(5), new Obstacle(5), null, new Dot(Color.RED), new Dot(Color.MEDIUMPURPLE),
                    new Dot(Color.LIMEGREEN), new Dot(Color.BLUE), new Dot(Color.YELLOW), new Dot(Color.LIMEGREEN), new Obstacle(10),
                    new Obstacle(2), null, new Obstacle(4), null, new Dot(Color.MEDIUMPURPLE)
            },
            {
                    new Dot(Color.BLUEVIOLET), new Obstacle(5), new Obstacle(10), new Dot(Color.BLUEVIOLET), new Dot(Color.PINK),
                    new Dot(Color.RED), new Obstacle(2), null, new Obstacle(5), new Obstacle(3),
                    new Obstacle(5), new Dot(Color.PINK), new Dot(Color.LIMEGREEN), null, new Obstacle(10),
                    null, new Dot(Color.RED), new Dot(Color.GOLD), new Obstacle(5), null,
                    new Dot(Color.BLUE), new Obstacle(1), new Dot(Color.BLUE), new Dot(Color.GOLD), new Dot(Color.LIMEGREEN)
            }
    }),
    HARD("flowfree/levels/Hard.fxml", 6, 45, new GridItem[][]{
            {
                    new Dot(Color.RED), null, new Obstacle(5), new Dot(Color.LIMEGREEN), null, new Obstacle(10),
                    new Dot(Color.MEDIUMPURPLE), new Obstacle(5), new Obstacle(10), new Obstacle(8), new Dot(Color.PLUM), new Dot(Color.LIMEGREEN),
                    new Dot(Color.ORANGE), null, new Dot(Color.MEDIUMPURPLE), null, null, new Obstacle(2),
                    new Obstacle(2), new Dot(Color.RED), null, new Obstacle(5), new Dot(Color.BLUE), null,
                    null, new Dot(Color.BLUE), new Obstacle(10), null, null, new Obstacle(2),
                    new Obstacle(5), null, new Dot(Color.ORANGE), new Dot(Color.PLUM), new Obstacle(10), null
            },
            {
                    new Dot(Color.BLUE), null, new Obstacle(5), new Obstacle(5), new Dot(Color.ORANGE), new Dot(Color.RED),
                    null, new Dot(Color.ORANGE), null, new Dot(Color.BLUE), new Obstacle(6), null,
                    new Obstacle(5), null, new Obstacle(10), new Obstacle(2), null, new Dot(Color.LIME),
                    new Dot(Color.MAGENTA), new Obstacle(2), new Dot(Color.YELLOW), new Dot(Color.RED), new Dot(Color.YELLOW), new Obstacle(2),
                    new Dot(Color.MEDIUMPURPLE), new Dot(Color.MAGENTA), null, new Obstacle(8), null, null,
                    new Obstacle(5), null, new Obstacle(8), new Dot(Color.MEDIUMPURPLE), new Dot(Color.LIME), new Obstacle(6)
            },
            {
                    new Dot(Color.CORNFLOWERBLUE), new Obstacle(5), new Dot(Color.CORNFLOWERBLUE), new Obstacle(5), new Dot(Color.LIME), new Dot(Color.BLUE),
                    new Obstacle(4), new Dot(Color.BLUEVIOLET), new Dot(Color.LIME), null, new Obstacle(8), null,
                    new Obstacle(10), new Dot(Color.RED), new Obstacle(10), new Dot(Color.BLUE), new Obstacle(5), new Dot(Color.DARKORANGE),
                    null, new Obstacle(10), null, new Obstacle(5), new Obstacle(4), null,
                    new Dot(Color.DARKTURQUOISE), null, new Dot(Color.BLUEVIOLET), null, new Dot(Color.DARKORANGE), new Dot(Color.MAGENTA),
                    new Obstacle(5), new Dot(Color.DARKTURQUOISE), new Dot(Color.RED), new Obstacle(8), new Dot(Color.MAGENTA), new Obstacle(4)
            }
    });

    private final String path;
    private final int size;
    private final int timerLimit;
    private final GridItem[][] levels;

    LevelDifficulty(String path, int size, int limit, GridItem[][] levels) {
        this.path = path;
        this.size = size;
        this.timerLimit = limit;
        this.levels = levels;
    }

    public String getPath() { return this.path; }
    public int getSize() { return this.size; }
    public int getTimerLimit() { return this.timerLimit; }

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
