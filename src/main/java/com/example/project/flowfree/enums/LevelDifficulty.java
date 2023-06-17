package com.example.project.flowfree.enums;

import com.example.project.flowfree.Dot;
import com.example.project.flowfree.Obstacle;
import javafx.scene.paint.Color;

public enum LevelDifficulty {
        EASY(3, new Object[][]{
                {new Obstacle(5), new Dot(Color.GOLD), null, null, null, new Dot(Color.BROWN), new Dot(Color.BLUE), null, null},
                {new Obstacle(10), null, new Dot(Color.GOLD), null, null, null, new Dot(Color.GOLD), null, null},
                {new Obstacle(15), null, null, null, null, new Dot(Color.GOLD), new Dot(Color.GOLD), null, null},
            }
        );

//        MEDIUM(4, new Object[][]{
//                { new Obstacle(10), new Dot(Color.GOLD), null, null, null, null, new Dot(Color.GOLD) },
//                { new Obstacle(10), new Dot(Color.GOLD), null, null, null, null, new Dot(Color.GOLD) },
//                { new Obstacle(10), new Dot(Color.GOLD), null, null, null, null, new Dot(Color.GOLD) },
//            }
//        );

        private final int size;
        private final Object[][] levels;

        LevelDifficulty(int size, Object[][] levels) {
            this.size = size;
            this.levels = levels;
        }

//           0 1 2
//        0 [1,2,3]
//        1 [1,2,3]
//        2 [1,2,3]
        public Object[][] level(int level) {
            Object[][] copiedLevel = new Object[size][size];
            if (level <= levels.length) {
                Object[] levelToCopy = levels[level];
                for (int i = 0; i < levelToCopy.length; i++) {
                    int x = i % size;
                    int y = i / size; //integer division
                    copiedLevel[x][y] = levelToCopy[i];
                }
            }
            return copiedLevel;
        }
        public int size() { return size; }
}
