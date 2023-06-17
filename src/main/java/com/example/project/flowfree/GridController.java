package com.example.project.flowfree;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;


// Work on update & Controllers, UI Elements
// 
//
public class GridController implements Initializable {
    @FXML private GridPane gridPane = new GridPane();

    private Grid grid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grid = new Grid();
        populate();
        handleEvent();
    }

    private int[] getXYfromIndex(int index) {
        int x = index % grid.getGridCells()[0].length;
        int y = index / grid.getGridCells()[0].length;
        System.out.println("x: " + x + " y: " + y);
        return new int[]{x, y};
    }

    private static String toRGBCode( Color color )
    {
        return String.format( "#%02X%02X%02X",
                (int)( color.getRed() * 255 ),
                (int)( color.getGreen() * 255 ),
                (int)( color.getBlue() * 255 ) );
    }

    // Find the way to set the size of gridPane because now its hardcoded staticly in the fxml
    private void populate() {
        Object[][] gridCells = grid.getGridCells();
        for (int i = 0; i < gridCells.length; i++) {
            for (int j = 0; j < gridCells[0].length; j++) {
                Pane pane = new Pane();
                if (gridCells[i][j] instanceof Dot) {
                    Dot dot = (Dot) gridCells[i][j];
                    pane.setStyle("-fx-background-color:" + toRGBCode(dot.getColor()));
                } else if (gridCells[i][j] instanceof Obstacle) {
                    Obstacle obstacle = (Obstacle) gridCells[i][j];
                    pane.getChildren().add(new Label(obstacle.getHitPoints() + ""));
                }
                gridPane.add(pane,i,j,1,1);
            }
        }
    }

    private void handleEvent() {
        gridPane.getChildren().forEach(item -> {
            item.addEventFilter(MouseDragEvent.DRAG_DETECTED, e -> {
                item.startFullDrag();
            });
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {
//                if (item instanceof ImageView) {
//                    ImageView gridCell = (ImageView) item;
//                    gridCell.setImage(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("images/x.png"))));
//                    item.setDisable(true);
//                }
                item.setStyle("-fx-background-color:green");
                grid.update(getXYfromIndex(gridPane.getChildren().indexOf(((Pane) e.getSource()))), Color.GREEN);
                System.out.println(Arrays.deepToString(grid.getGridCells()));
            });
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {
                // endIndex
//                if (item instanceof ImageView) {
//                    System.out.println(gridPane.getChildren().indexOf(((ImageView) e.getSource())));
//                }
                getXYfromIndex(gridPane.getChildren().indexOf(((Pane) e.getSource())));
            });
            item.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                int[] temp = getXYfromIndex(gridPane.getChildren().indexOf(((Pane) e.getSource())));
                grid.update(temp);
                ((Label)((Pane) item).getChildren().get(0)).setText(grid.get);
            });
        });
    }
}
