package com.example.project.flowfree;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.Flow;

// Work on update & Controllers, UI Elements
public class GridController implements Initializable {
    @FXML private GridPane gridPane = new GridPane(); // for FXML
    private Grid grid = new Grid(); // for Code Logic

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populate();
        handleEvent();
    }

    private int[] getXYfromIndex(int index) {
        int y = index % grid.getGridCells()[0].length;
        int x = index / grid.getGridCells()[0].length;
//        System.out.println("x: " + x + " y: " + y);
        return new int[]{x, y};
    }

    private void populate() {
        GridItem[][] gridCells = grid.getGridCells();
        for (int i = 0; i < gridCells.length; i++) {
            for (int j = 0; j < gridCells[0].length; j++) {
                GridItem gridItem = gridCells[i][j];
                FFPane pane = new FFPane(gridItem);
                if (gridItem instanceof ColoredGridItem) {
                    ColoredGridItem coloredGridItem = (ColoredGridItem) gridItem;
                    if (coloredGridItem instanceof Dot) {
                        pane.setStyle("-fx-background-color:" + (coloredGridItem.getHexColor()));
                    }
                } else if (gridItem instanceof Obstacle) {
                    pane.getChildren().add(new Label(((Obstacle) gridItem).getHitPoints() + ""));
                    pane.getChildren().get(0).setTranslateX(20);
                }
                gridPane.add(pane,j,i,1,1);
            }
        }
    }

    private void handleEvent() {
        ArrayList<Integer> indices = new ArrayList<>();
        gridPane.getChildren().forEach(item -> {
            if (item instanceof Group) {
                return;
            }
            item.addEventFilter(MouseDragEvent.DRAG_DETECTED, e -> {
                Pane temp = (Pane) e.getSource();
                int[] temp1 = getXYfromIndex(gridPane.getChildren().indexOf(temp)-1);
                if (grid.getGridCells()[temp1[0]][temp1[1]] instanceof Dot) {
                    indices.clear();
                    item.startFullDrag();
                    indices.add(gridPane.getChildren().indexOf(temp)-1);
                }
            });
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {
                Pane temp = (Pane) e.getSource();
                int[] temp1 = getXYfromIndex(gridPane.getChildren().indexOf(temp)-1);
                if (grid.getGridCells()[temp1[0]][temp1[1]] == null) {
                    item.setStyle("-fx-background-color:green");
                    indices.add(gridPane.getChildren().indexOf(temp)-1);
                }
            });
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {
                Pane temp = (Pane) e.getSource();
                int[] temp1 = getXYfromIndex(gridPane.getChildren().indexOf(temp)-1);
                int[] temp2 = getXYfromIndex(indices.get(0));
                int[] temp3 = getXYfromIndex(indices.get(indices.size()-1));
                Object o1 = grid.getGridCells()[temp2[0]][temp2[1]];
                Object o2 = grid.getGridCells()[temp3[0]][temp3[1]];
                System.out.println(o1);
                System.out.println(o2);

            });
            // Error when clicking on non-Obstacle Tile
            item.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                Pane temp = (Pane) e.getSource();
                int[] temp1 = getXYfromIndex(gridPane.getChildren().indexOf(temp)-1);
                if (grid.getGridCells()[temp1[0]][temp1[1]] instanceof Obstacle) {
                    if (!grid.update(temp1)) {
                        ((Label) temp.getChildren().get(0)).setText(((Obstacle) grid.getGridCells()[temp1[0]][temp1[1]]).getHitPoints() + "");
                    }
//                    temp.getChildren().clear();
                }
            });
        });
    }
}
