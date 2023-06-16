package com.example.project.flowfree;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LevelController implements Initializable {
    @FXML
    private GridPane gridPane;

    public void initialize(URL arg0, ResourceBundle arg1) {
        handleEvent();
    }

    private void handleEvent() {
//        gridPane.getChildren().forEach(item -> {
//            item.addEventFilter(MouseDragEvent.DRAG_DETECTED, e -> {
//                item.startFullDrag();
//            });
//            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {
//                item.setStyle("-fx-background-color:red");
//                System.out.println(gridPane.getChildren().indexOf(((Pane) e.getSource())));
//                System.out.println(gridPane.getChildren().indexOf(((Pane) e.getTarget())));
//            });
//        });
        gridPane.getChildren().forEach(item -> {
            item.addEventFilter(MouseDragEvent.DRAG_DETECTED, e -> {
                item.startFullDrag();
            });
            item.addEventFilter(MouseDragEvent.MOUSE_DRAG_ENTERED, e -> {
                if (item instanceof ImageView) {
                    ImageView gridCell = (ImageView) item;
                    gridCell.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/x.png"))));
                    item.setDisable(true);
                }
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
