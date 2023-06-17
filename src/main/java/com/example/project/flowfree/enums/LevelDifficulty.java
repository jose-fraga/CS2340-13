package com.example.project.flowfree.enums;

import com.example.project.flowfree.Dot;
import com.example.project.flowfree.GridItem;
import com.example.project.flowfree.Obstacle;
import com.example.project.flowfree.Pipe;
import javafx.scene.paint.Color;

public enum LevelDifficulty {
        EASY(4, new GridItem[][]{
                {
                        new Dot(Color.GREEN), new Dot(Color.RED), new Dot(Color.BLUE), new Dot(Color.RED),
                        null, null, new Obstacle(1), null,
                        null, null, new Dot(Color.BLUE), null,
                        new Dot(Color.GREEN), null, new Obstacle(10), null
                },
                {new Obstacle(10), null, new Dot(Color.GOLD), null, null, null, new Dot(Color.GOLD), null, null},
                {new Obstacle(15), null, null, null, null, new Dot(Color.GOLD), new Dot(Color.GOLD), null, null},
            }
        );

//        MEDIUM(4, new GridItem[][]{
//                { new Obstacle(10), new Dot(Color.GOLD), null, null, null, null, new Dot(Color.GOLD) },
//                { new Obstacle(10), new Dot(Color.GOLD), null, null, null, null, new Dot(Color.GOLD) },
//                { new Obstacle(10), new Dot(Color.GOLD), null, null, null, null, new Dot(Color.GOLD) },
//            }
//        );

        private final int size;
        private final GridItem[][] levels;

        LevelDifficulty(int size, GridItem[][] levels) {
            this.size = size;
            this.levels = levels;
        }

//          0 1 2
//       0 [1,2,3]
//       1 [1,2,3]
//       2 [1,2,3]
        public GridItem[][] level(int level) {
            GridItem[][] copiedLevel = new GridItem[size][size];
            if (level >= 0 && level < levels.length) {
                GridItem[] levelToCopy = levels[level];
                for (int i = 0; i < levelToCopy.length; i++) {
                    int y = i % size;
                    int x = i / size; //integer division
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
