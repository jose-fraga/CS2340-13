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

    private Level level;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populate();
        handleEvent();
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

    private void resetPipePath() {
        while (!pipePaths.isEmpty()) {
            FFPane curr = pipePaths.pop();
            ((Pipe) curr.getGridItem()).reset();
            curr.setStyle("-fx-background-color: transparent");
        }
        activeDot = null;
    }

    private void handleEvent() {
        gridPane.getChildren().forEach(item -> {
            if (item instanceof Group) return;

            item.addEventFilter(MouseDragEvent.DRAG_DETECTED, e -> {
                FFPane itemPane = (FFPane) e.getSource();
                if (itemPane.getGridItem() instanceof Dot ) {
                    activeDot = (Dot) itemPane.getGridItem();
                    item.startFullDrag();
                } else {
                    activeDot = null;
                }
            });

            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {
                System.out.println("Mouse Drag Entered: " + item);
                if (activeDot == null) return;

                FFPane itemPane = (FFPane) e.getSource();
                GridItem gridItem = itemPane.getGridItem();
                if (gridItem instanceof Pipe && ((Pipe) gridItem).isEmpty()) {
                    pipePaths.add(itemPane);
                    Pipe pipe = (Pipe) gridItem;
                    pipe.tempFill(activeDot.getColor());
                    item.setStyle("-fx-background-color:" + (activeDot.getHexColor()));
                } else if (!pipePaths.isEmpty() && !activeDot.isConnectingDot(gridItem)) {
                    System.out.println("BAD CONNECTION");
                    resetPipePath();
                }



//                else if (gridItem instanceof Dot) {
//                    Dot currentDot = (Dot) gridItem;
//                    System.out.println("Current dot different: " + (currentDot != activeDot));
//                    System.out.println("Current colors equal: " + currentDot.getColor().equals(activeDot.getColor()));
//                    System.out.println(currentDot + " " + activeDot);
//                    if (currentDot != activeDot && currentDot.getColor().equals(activeDot.getColor())){
//                        // VALID CONNECTION
//                        System.out.println("VALID: " + currentDot.getColor());
//                    } else {
//                        // INVALID CONNECTION
//                        System.out.println("INVALID: " + currentDot.getColor() + ", " + activeDot.getColor());
//                        resetPipePath();
//                    }
//                }
//                else {
//
//                    // not a pipe completion and not an empty space, means invalid cell like other pipe or obstacle
//                    resetPipePath();
//                }
            });

            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {
                FFPane itemPane = (FFPane) e.getSource();
                GridItem gridItem = itemPane.getGridItem();

                System.out.println(pipePaths);

                if ((pipePaths.size() == 0)) {
                    // pipePath has no elements
                    System.out.println("FAILURE #1");
                    pipePaths.clear();
                    return;
                }
                

                if ((gridItem instanceof Dot) && (activeDot != gridItem) && (activeDot.getColor().equals(((Dot) gridItem).getColor()))) {
                    // Released On Matching Dot -> Check Pipes
                    if (checkPipe()) {
                        System.out.println("SUCCESS");
                        pipePaths.clear();
                    } else {
                        // pipePath has incorrect objects
                        System.out.println("FAILURE #2");
                        resetPipePath();
                        pipePaths.clear();
                    }
                } else {
                    // Released On Non-Dot -> Reset & Clear Pipe
                    System.out.println("FAILURE #3");
                    resetPipePath();
                    pipePaths.clear();
                }

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

    private boolean checkPipe() {
        for (int i = 0; i < pipePaths.size(); i++) {
            FFPane curr = pipePaths.get(i);
            if ((curr.getGridItem() instanceof Pipe) & (activeDot.getColor().equals(((Pipe) curr.getGridItem()).getColor()))) {
//                ((Pipe) curr.getGridItem()).setPipeState(PipeState.FILLED_FINAL);
                ((Pipe) curr.getGridItem()).finalize();
            } else {
                // pipePath contains non-Pipe || pipe wrong color
                return false;
            }
        }
        System.out.println(pipePaths);
        return true;
    }
}
