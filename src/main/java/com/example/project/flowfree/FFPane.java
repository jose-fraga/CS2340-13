package com.example.project.flowfree;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class FFPane extends StackPane {
    private GridItem gridItem;

    public FFPane(GridItem gridItem) { this.gridItem = gridItem; }
    public FFPane(GridItem gridItem, Node content) {
        super(content);
        this.gridItem = gridItem;
    }

    public GridItem getGridItem() { return this.gridItem; }

    @Override
    public String toString() {
        return String.format("FFPane(%s)", this.gridItem.toString());
    }
}
