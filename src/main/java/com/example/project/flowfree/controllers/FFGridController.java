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
import com.example.project.flowfree.enums.Warning;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    @FXML private Label timerDisplay;
    @FXML private Label pauseLabel;
    @FXML private Label warningLabel;

    private final String BORDER_STYLE = "-fx-border-width: 1px; -fx-border-color: grey;";

    private Level level;
    private Grid grid;
    private Dot activeDot;
    private LinkedList<FFPane> pipePaths = new LinkedList<>();

    private boolean isDragging;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeGrid();
        initializeTimer();
    }

    private void initializeGrid() {
        this.level = FFGame.getGameInstance().getLevel();
        this.grid = this.level.getGrid();
        populate();
        handleEvent();
    }

    private void initializeTimer() {
        System.out.println(level.getTimer().toString());
        if (!this.level.getTimer().isStarted()) {
            this.level.getTimer().start();
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        timerDisplay.setText(level.getTimer().toString().substring(3, 8));

                        int timeLimit = Integer.parseInt(level.getTimer().toString().substring(6, 8));
                        // Game ends if timer runs of (Current limit: 1 min)
                        if (timeLimit == 30) {
                            FFEndController.isSuccess = false;
                            Helper.changeGameScreen("flowfree/FFEndScreen.fxml");
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

    @FXML private void toggleTimer(ActionEvent e) {
        if (level.isPaused()) { // Resume timer if paused
            level.resume();
            toggleButton.setText("Pause");
            gridPane.setVisible(true);
            pauseLabel.setVisible(false);
        } else { // Pause timer if running
            toggleButton.setText("Resume");
            level.pause();
            gridPane.setVisible(false);
            pauseLabel.setVisible(true);
        }
    }

    private void populate() {
        gridPane.getChildren().clear();
        GridItem[][] gridCells = grid.getGridCells();
        for (int i = 0; i < gridCells.length; i++) {
            for (int j = 0; j < gridCells[0].length; j++) {
                GridItem gridItem = gridCells[i][j];
                FFPane pane = new FFPane(gridItem);
                pane.setStyle(BORDER_STYLE);
                if (gridItem instanceof Obstacle) {
                    Label curr = new Label(((Obstacle) gridItem).getHitPoints() + "");
                    curr.setFont(Font.font("Gill Sans Ultra Bold Condensed", 15));
                    pane.getChildren().add(curr);
                    pane.setAlignment(curr, Pos.CENTER);
                } else if (gridItem instanceof ColoredGridItem) {
                    ColoredGridItem coloredGridItem = (ColoredGridItem) gridItem;
                    if (coloredGridItem instanceof Dot) {
                        pane.setStyle("-fx-background-color:" + coloredGridItem.getHexColor());
                    }
                }
                gridPane.add(pane,j,i,1,1);
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
                    item.setStyle("-fx-background-color:" + (activeDot.getHexColor()));
                } else if (!pipePaths.isEmpty() && !activeDot.isConnectingDot(gridItem)) {
//                    System.out.println("BAD CONNECTION");
                    displayWarning(Warning.FAILURE_2.getMessage());
                    resetPipePath();
                    return;
                }
            });

            // Checks for successfull drag between Dots
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {
                FFPane itemPane = (FFPane) e.getSource();
                GridItem gridItem = itemPane.getGridItem();
                if ((pipePaths.size() == 0)) {
                    // pipePaths has no elements
//                    System.out.println("FAILURE #1 - PipePaths is Empty");
                    displayWarning(Warning.FAILURE_3.getMessage());
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
//                        System.out.println("FAILURE #2 - PipePaths has Invalid Objects");
                        displayWarning(Warning.FAILURE_4.getMessage());
                        resetPipePath();
                        pipePaths.clear();
                        return;
                    }
                } else {
                    // Released On non-Dot object -> Reset & clear pipePaths
//                    System.out.println("FAILURE #3 - Drag released on non-Dot Object");
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
            curr.setStyle("-fx-background-color: transparent;" + BORDER_STYLE);
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

    @FXML private void returnToLevelSelect(ActionEvent e) {
        Helper.changeGameScreen(Helper.currentGame.gameFxmlPath());
    }

    @FXML private void restartLevel(ActionEvent e) {
        if (!gridPane.isVisible()) {
            gridPane.setVisible(true);
            pauseLabel.setVisible(false);
            toggleButton.setText("Pause");
        }
        level.restart();
        initializeGrid();
    }
}
