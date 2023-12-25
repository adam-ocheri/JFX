package com.datadis.javafx_dnd;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;



public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button source;

    @FXML
    private Button destination;

    @FXML
    private Pane rootPane;

    private class Delta {
        double x, y;
    }

    public void initialize() {

        welcomeText.setText("INIT");

        source.setOnMousePressed(event -> {
            source.relocate(event.getSceneX(), event.getSceneY());
        });

        source.setOnMouseDragged(event -> {
            source.setLayoutX(event.getSceneX() - rootPane.getLayoutX());
            source.setLayoutY(event.getSceneY() - rootPane.getLayoutY());
        });

        source.setOnDragDetected(event -> {
            Dragboard db = source.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(source.getText());
            db.setContent(content);
            event.consume();
        });

        source.setOnDragDone(event -> {
            if(event.getTransferMode() == TransferMode.MOVE) {
                source.setText("");
            }
        });

        destination.setOnDragOver(event -> {
            /* data is dragged over the target */
            /* accept it only if it has a string data */
            if (event.getGestureSource() != destination &&
                    event.getDragboard().hasString()) {
                /* allow for both copying and moving, whatever user chooses */
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }

            event.consume();
        });

        destination.setOnDragDropped(event -> {
            /* data dropped */
            /* if there is a string data on dragboard, read it and use it */
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                destination.setText(db.getString());
                success = true;
            }
            /* let the source know whether the string was successfully
             * transferred and used */
            event.setDropCompleted(success);

            event.consume();
        });
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Cookie Shmerkler is in the house!");
    }


}