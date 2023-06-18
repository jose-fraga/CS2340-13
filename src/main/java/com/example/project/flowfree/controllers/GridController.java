package com.example.project.flowfree.controllers;

import com.example.project.Helper;
import com.example.project.flowfree.ColoredGridItem;
import com.example.project.flowfree.Dot;
import com.example.project.flowfree.FFGame;
import com.example.project.flowfree.FFPane;
import com.example.project.flowfree.Grid;
import com.example.project.flowfree.GridItem;
import com.example.project.flowfree.Level;
import com.example.project.flowfree.Obstacle;
import com.example.project.flowfree.Pipe;
import javafx.animation.Animation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class GridController implements Initializable {
    @FXML private GridPane gridPane = new GridPane();
    @FXML private Button pauseButton;
    @FXML private Label timer;

    private Level level;
    private Grid grid;
    private Dot activeDot;
    private LinkedList<FFPane> pipePaths = new LinkedList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.level = FFGame.getGameInstance().getLevel();
        this.grid = FFGame.getGameInstance().getLevel().getGrid();


        populate();
        handleEvent();
    }

    private void pauseTimer(ActionEvent actionEvent) {

    }

    private void populate() {
        GridItem[][] gridCells = grid.getGridCells();
        for (int i = 0; i < gridCells.length; i++) {
            for (int j = 0; j < gridCells[0].length; j++) {
                GridItem gridItem = gridCells[i][j];
                FFPane pane = new FFPane(gridItem);
                if (gridItem instanceof Obstacle) {
                    pane.getChildren().add(new Label(((Obstacle) gridItem).getHitPoints() + ""));
                    pane.getChildren().get(0).setTranslateX(20);
                } else if (gridItem instanceof ColoredGridItem) {
                    ColoredGridItem coloredGridItem = (ColoredGridItem) gridItem;
                    if (coloredGridItem instanceof Dot) {
                        pane.setStyle("-fx-background-color:" + (coloredGridItem.getHexColor()));
                    }
                }
                gridPane.add(pane,j,i,1,1);
            }
        }
    }

    private void handleEvent() {
        gridPane.getChildren().forEach(item -> {
            if (item instanceof Group) return;
            timer.setText(level.timer.getSspTime().get());
            System.out.println(level.timer.getSspTime().get());

            // Starts drags from Dots
            item.addEventFilter(MouseDragEvent.DRAG_DETECTED, e -> {
                FFPane itemPane = (FFPane) e.getSource();
                if (itemPane.getGridItem() instanceof Dot ) {
                    activeDot = (Dot) itemPane.getGridItem();
                    item.startFullDrag();
                } else {
                    activeDot = null;
                }
            });

            // Checks full drag from Dots
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {
                if (activeDot == null) return;

                FFPane itemPane = (FFPane) e.getSource();
                GridItem gridItem = itemPane.getGridItem();
                if (gridItem instanceof Pipe && ((Pipe) gridItem).getIsEmpty()) {
                    pipePaths.add(itemPane);
                    Pipe pipe = (Pipe) gridItem;
                    pipe.tempFill(activeDot.getColor());
                    item.setStyle("-fx-background-color:" + (activeDot.getHexColor()));
                } else if (!pipePaths.isEmpty() && !activeDot.isConnectingDot(gridItem)) {
                    System.out.println("BAD CONNECTION");
                    resetPipePath();
                }
            });

            // Checks for successfull drag between Dots
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {
                FFPane itemPane = (FFPane) e.getSource();
                GridItem gridItem = itemPane.getGridItem();
                if ((pipePaths.size() == 0)) {
                    // pipePaths has no elements
                    System.out.println("FAILURE #1 - PipePaths is Empty");
                    pipePaths.clear();
                    return;
                }
                if ((gridItem instanceof Dot) && (activeDot != gridItem) && (activeDot.getColor().equals(((Dot) gridItem).getColor()))) {
                    // Drag released on matching dots -> checkPipes()
                    if (checkPipes()) {
                        System.out.println("SUCCESS!");
                        if (grid.isComplete()) {
                            System.out.println("LEVEL COMPLETE!");
                            Helper.changeGameScreen("flowfree/FFEndScreen.fxml");
                        } else {
                            System.out.println("KEEP GOING...");
                        }
                        pipePaths.clear();
                    } else {
                        // pipePath has non-Pipe objects
                        System.out.println("FAILURE #2 - PipePaths has Invalid Objects");
                        resetPipePath();
                        pipePaths.clear();
                    }
                } else {
                    // Released On non-Dot object -> Reset & clear pipePaths
                    System.out.println("FAILURE #3 - Drag released on non-Dot Object");
                    resetPipePath();
                    pipePaths.clear();
                }
            });

            // Destorys Obstacles
            item.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                FFPane pane = (FFPane) e.getSource();
                if (pane.getGridItem() instanceof Obstacle) {
                    Obstacle obstacle = (Obstacle) pane.getGridItem();
                    if (obstacle.isCleared()) return;
                    if (!obstacle.destroy()) {
                        ((Label) pane.getChildren().get(0)).setText(((Obstacle) pane.getGridItem()).getHitPoints() + "");
                    } else {
                        pane.getChildren().clear();
                    }
                }
            });
        });
    }

    private void resetPipePath() {
        while (!pipePaths.isEmpty()) {
            FFPane curr = pipePaths.pop();
            ((Pipe) curr.getGridItem()).resetFill();
            curr.setStyle("-fx-background-color: transparent");
        }
        activeDot = null;
    }

    private boolean checkPipes() {
        for (int i = 0; i < pipePaths.size(); i++) {
            FFPane curr = pipePaths.get(i);
            if ((curr.getGridItem() instanceof Obstacle) && !((Obstacle) curr.getGridItem()).isCleared()) {
                return false;
            }
            if ((curr.getGridItem() instanceof Pipe) & (activeDot.getColor().equals(((Pipe) curr.getGridItem()).getColor()))) {
                ((Pipe) curr.getGridItem()).finalizeFill();
            } else {
                return false;
            }
        }
        return true;
    }
}
