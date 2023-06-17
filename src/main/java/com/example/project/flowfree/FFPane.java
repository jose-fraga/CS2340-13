package com.example.project.flowfree;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class FFPane extends Pane {
    private GridItem gridItem;

    public GridItem getGridItem() { return this.gridItem; }
    public void setGridItem(GridItem gridItem) { this.gridItem = gridItem; }

    public FFPane(GridItem gridItem) {
        this.gridItem = gridItem;
    }

    public FFPane(GridItem gridItem, Node content) {
        super(content);
        this.gridItem = gridItem;
    }
}
