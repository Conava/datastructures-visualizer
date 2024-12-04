package org.conava.dsv.app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.conava.dsv.modules.*;

public class MainController {

    @FXML
    private Label lastCommandLabel;

    @FXML
    private Pane moduleContainer;

    private DataStructureModule currentModule;

    @FXML
    private void handleUndo() {
        // Implement undo functionality
        updateLastCommand("Undo");
    }

    @FXML
    private void handleRedo() {
        // Implement redo functionality
        updateLastCommand("Redo");
    }

    @FXML
    /**
     * Handles the quit button click event.
     * Close the application.
     */
    private void handleQuit() {
        Platform.exit();
    }

    private void updateLastCommand(String command) {
        lastCommandLabel.setText(command);
    }

    @FXML
    private void handleLinkedList() {
        loadModule(new LinkedListModule());
    }

    @FXML
    private void handleHashtable() {
        loadModule(new HashTableModule());
    }

    @FXML
    private void handleGraph() {
        loadModule(new GraphModule());
    }

    private void loadModule(DataStructureModule module) {
        currentModule = module;
        moduleContainer.getChildren().clear();
        moduleContainer.getChildren().add(module.getModuleUI());
    }
}
