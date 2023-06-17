package com.example.project.flowfree;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class GridController implements Initializable {
    @FXML private GridPane gridPane = new GridPane(); // for FXML

    private Grid grid = new Grid(); // for Code Logic
    private Dot activeDot;
    private LinkedList<FFPane> pipePaths = new LinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populate();
        handleEvent();
    }

    private int[] getXYfromIndex(int index) {
        int y = index % grid.getGridCells()[0].length;
        int x = index / grid.getGridCells()[0].length;
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

    // Reset drag when end dot is not same as start dot
    // Level's isCompleted
    // Show Player sprite and name on game

    private void resetPipePath() {
        for (int i = 0; i < pipePaths.size(); i++) {
            FFPane curr = pipePaths.get(i);
            curr.getGridItem().setIsEmpty(true);
            ((Pipe) curr.getGridItem()).setPipeState(PipeState.EMPTY);
            curr.setStyle("-fx-background-color:white");
        }
    }

    private void handleEvent() {
        gridPane.getChildren().forEach(item -> {
            if (item instanceof Group) {
                return;
            }

            item.addEventFilter(MouseDragEvent.DRAG_DETECTED, e -> {
                FFPane itemPane = (FFPane) e.getSource();
                if (itemPane.getGridItem() instanceof Dot ) {
                    activeDot = (Dot) itemPane.getGridItem();
                    item.startFullDrag();
                }
            });

            // Half way should reset
            // First drag is not resetting
            // If dots connected, then don't reset
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {
                FFPane itemPane = (FFPane) e.getSource();
                GridItem gridItem = itemPane.getGridItem();
                if (gridItem instanceof Pipe && ((Pipe) gridItem).isEmpty()) {
                    pipePaths.add(itemPane);
                    Pipe pipe = (Pipe) gridItem;
                    pipe.tempFill(activeDot.getColor());
                    item.setStyle("-fx-background-color:" + (activeDot.getHexColor()));
                } else {

                }
            });

            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {
                FFPane itemPane = (FFPane) e.getSource();

//                if (itemPane.getGridItem() instanceof Dot) {
//                    Dot currentDot = (Dot) itemPane.getGridItem();
//                    if (currentDot.getColor() == this.activeDot.getColor()) {
//                        System.out.println("COMPLETE");
//                    } else {
//                        System.out.println("RESET");
//                    }
//                }
            });

            item.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                FFPane pane = (FFPane) e.getSource();
                if (pane.getGridItem() instanceof Obstacle) {
                    Obstacle obstacle = (Obstacle) pane.getGridItem();
                    int[] values = {obstacle.getX(), obstacle.getY()};
                    if (!obstacle.destroy()) {
                        ((Label) pane.getChildren().get(0)).setText(((Obstacle) pane.getGridItem()).getHitPoints() + "");
                    } else {
                        pane.getChildren().clear();
                        pane.setGridItem(new Pipe(values[0], values[1]));
                    }
                }
            });
        });
    }
}
