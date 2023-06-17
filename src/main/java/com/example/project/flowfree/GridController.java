package com.example.project.flowfree;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class GridController implements Initializable {
    @FXML private GridPane gridPane = new GridPane();

    private Grid grid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grid = new Grid();
        handleEvent();
        gridPane.getChildren().forEach(item -> {
            item = new Pane();
        });
//        gridPane.add(new Pane(),0,0);
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
                item.setStyle("-fx-border-color:green");
            });
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> {
                // endIndex
                if (item instanceof ImageView) {
                    System.out.println(gridPane.getChildren().indexOf(((ImageView) e.getSource())));
                }
            });
        });
    }
}
