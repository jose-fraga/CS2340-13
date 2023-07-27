package com.example.project.flowfree.controllers;

import com.example.project.Game;
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
import com.example.project.flowfree.enums.Warning;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class FFGridController implements Initializable {
    @FXML private GridPane gridPane;
    @FXML private Button toggleButton;
    @FXML private Label timerDisplay, pauseLabel, warningLabel;

    private LinkedList<FFPane> pipePaths;
    private Level level;
    private Grid grid;
    private Dot activeDot;
    private Timer timer;
    private boolean isDragging;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeGrid();
        initializeTimer();
    }

    private void initializeGrid() {
        level = FFGame.getGameInstance().getLevel();
        grid = level.getGrid();
        pipePaths = new LinkedList<>();
        populate();
        handleEvent();
    }

    private void initializeTimer() {
        if (!level.getTimer().isStarted()) {
            level.getTimer().start();
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timerDisplay.setText(String.valueOf(level.getSecondsLeft()));

                        if (level.getSecondsLeft() <= 0) {
                            FXMLLoader loader = safelyChangeScreen("flowfree/FFEndScreen.fxml");
                            FFEndController controller = loader.<FFEndController>getController();
                            controller.showFailureMessage();
                        }

                        if (warningLabel.isVisible()) {
                            level.pause();
                            synchronized (timer) {
                                try {
                                    timer.wait(1000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            warningLabel.setVisible(false);
                            level.resume();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    private void populate() {
        GridItem[][] gridCells = grid.getGridCells();
        for (int i = 0; i < gridCells.length; i++) {
            for (int j = 0; j < gridCells[0].length; j++) {
                GridItem gridItem = gridCells[i][j];
                FFPane pane = new FFPane(gridItem);
                if (gridItem instanceof Obstacle) {
                    Label curr = new Label(((Obstacle) gridItem).getHitPoints() + "");
                    curr.setFont(Font.font("Gill Sans Ultra Bold Condensed", 15));
                    pane.getChildren().add(curr);
                    pane.setAlignment(curr, Pos.CENTER);
                } else if (gridItem instanceof ColoredGridItem) {
                    ColoredGridItem coloredGridItem = (ColoredGridItem) gridItem;
                    if (coloredGridItem instanceof Dot) {
                        pane.setStyle(
                                "-fx-background-insets: 1; -fx-border-color: black; " +
                                "-fx-background-color:" + coloredGridItem.getHexColor()
                        );
                    }
                }
                gridPane.add(pane,j,i);
            }
        }
    }

    private void handleEvent() {
        gridPane.getChildren().forEach(item -> {
            gridPane.setOnMouseExited(e1 -> {
//              System.out.println("FAILURE #1 - Drag Exited Pane!");
                if (isDragging) {
                    displayWarning(Warning.FAILURE_1.getMessage());
                    activeDot = null;
                    resetPipePath();
                    pipePaths.clear();
                }
                return;
            });

            if (item instanceof Group) return;

            // Starts drags from Dots
            item.addEventFilter(MouseDragEvent.DRAG_DETECTED, e -> {
                FFPane itemPane = (FFPane) e.getSource();
                if (itemPane.getGridItem() instanceof Dot ) {
                    activeDot = (Dot) itemPane.getGridItem();
                    item.startFullDrag();
                    isDragging = true;
                } else {
                    activeDot = null;
                }
            });

            // Checks full drag from Dots
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {
                if (activeDot == null) { return; }
                FFPane itemPane = (FFPane) e.getSource();
                GridItem gridItem = itemPane.getGridItem();
                if (gridItem instanceof Pipe && ((Pipe) gridItem).getIsEmpty()) {
                    pipePaths.add(itemPane);
                    Pipe pipe = (Pipe) gridItem;
                    pipe.tempFill(activeDot.getColor());
                    item.setStyle(
                            "-fx-background-insets: 1; -fx-border-color: black; " +
                            "-fx-background-color: " + (activeDot.getHexColor())
                    );
                } else if (!pipePaths.isEmpty() && !activeDot.isConnectingDot(gridItem)) {
//                  System.out.println("BAD CONNECTION");
                    displayWarning(Warning.FAILURE_2.getMessage());
                    resetPipePath();
                    return;
                }
            });

            // Checks for successfull drag between Dots
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {
                FFPane itemPane = (FFPane) e.getSource();
                GridItem gridItem = itemPane.getGridItem();
                // pipePaths has no elements
                if ((pipePaths.size() == 0)) {
//                  System.out.println("FAILURE #1 - PipePaths is Empty");
                    displayWarning(Warning.FAILURE_3.getMessage());
                    pipePaths.clear();
                    return;
                }
                // Drag released on matching dots -> checkPipes()
                if ((gridItem instanceof Dot) && (activeDot != gridItem) && (activeDot.getColor().equals(((Dot) gridItem).getColor()))) {
                    if (checkPipes()) {
                        System.out.println("SUCCESS!");
                        if (grid.isComplete()) {
                            level.complete();
                            System.out.println("LEVEL COMPLETE!");
                            safelyChangeScreen("flowfree/FFEndScreen.fxml");

                        } else {
                            System.out.println("KEEP GOING...");
                        }
                        pipePaths.clear();
                    // pipePath has non-Pipe objects
                    } else {
//                      System.out.println("FAILURE #2 - PipePaths has Invalid Objects");
                        displayWarning(Warning.FAILURE_4.getMessage());
                        resetPipePath();
                        pipePaths.clear();
                        return;
                    }
                // Released On non-Dot object -> Reset & clear pipePaths
                } else {
//                  System.out.println("FAILURE #3 - Drag released on non-Dot Object");
                    displayWarning(Warning.FAILURE_2.getMessage());
                    resetPipePath();
                    pipePaths.clear();
                    return;
                }
                isDragging = false;
            });

            // Destorys Obstacles
            item.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                FFPane pane = (FFPane) e.getSource();
                if (pane.getGridItem() instanceof Obstacle) {
                    Obstacle obstacle = (Obstacle) pane.getGridItem();
                    if (obstacle.isCleared()) { return; }
                    if (!obstacle.destroy()) {
                        ((Label) pane.getChildren().get(0)).setText(((Obstacle) pane.getGridItem()).getHitPoints() + "");
                    } else {
                        pane.getChildren().clear();
                    }
                }
            });
        });
    }

    private void displayWarning(String message) {
        warningLabel.setVisible(true);
        warningLabel.setText(message);
        isDragging = false;
    }

    private void resetPipePath() {
        while (!pipePaths.isEmpty()) {
            FFPane curr = pipePaths.pop();
            ((Pipe) curr.getGridItem()).resetFill();
            curr.setStyle("-fx-background-color: transparent;");
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

    private FXMLLoader safelyChangeScreen(String fxmlPath) {
        timer.cancel();
        return Helper.changeGameScreen(fxmlPath);
    }

    @FXML private void toggleTimer(ActionEvent e) {
        // If Paused, Resume Timer
        if (level.isPaused()) {
            level.resume();
            toggleButton.setText("Pause");
            gridPane.setVisible(true);
            pauseLabel.setVisible(false);
            // If Running, Pause Timer
        } else {
            toggleButton.setText("Resume");
            level.pause();
            gridPane.setVisible(false);
            pauseLabel.setVisible(true);
        }
    }

    @FXML private void returnToLevelSelect(ActionEvent e) {
        safelyChangeScreen(Game.FLOW.gameFxmlPath());
    }

    @FXML private void restartLevel(ActionEvent e) {
        if (!gridPane.isVisible()) {
            gridPane.setVisible(true);
            pauseLabel.setVisible(false);
            toggleButton.setText("Pause");
        }
        gridPane.getChildren().subList(1, gridPane.getChildren().size()).clear();
        timerDisplay.setText(Level.TIME_LIMIT + "");
        level.restart();
        initializeGrid();
    }
}
