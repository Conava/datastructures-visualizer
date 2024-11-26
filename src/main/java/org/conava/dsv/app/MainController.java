package org.conava.dsv.app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.conava.dsv.modules.DataStructureModule;
import org.conava.dsv.commands.CommandManager;

public class MainController {

    @FXML
    private BorderPane rootPane;
    @FXML
    private Button undoButton;
    @FXML
    private Button redoButton;
    @FXML
    private Button quitButton;

    private DataStructureModule currentModule;
    private CommandManager commandManager = new CommandManager();

    public void initialize() {
        // Initialize buttons and attach event handlers
    }

    public void loadModule(DataStructureModule module) {
        currentModule = module;
        rootPane.setCenter(module.getView());
    }

    @FXML
    private void handleUndo() {
        commandManager.undo();
    }

    @FXML
    private void handleRedo() {
        commandManager.redo();
    }

    @FXML
    private void handleQuit() {
        // Close application
    }

    @FXML
    private void handleSelectDataStructure() {
        // Show data structure selection dialog
    }
}