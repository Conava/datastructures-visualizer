package org.conava.dsv.app;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.conava.dsv.commands.CommandManager;
import org.conava.dsv.modules.*;
import org.conava.dsv.modules.binaryTree.BinaryTreeModule;
import org.conava.dsv.modules.graph.GraphModule;
import org.conava.dsv.modules.hashTable.HashTableModule;
import org.conava.dsv.modules.linkedList.LinkedListModule;

public class MainController {

    @FXML
    private Button undoButton;

    @FXML
    private Button redoButton;

    @FXML
    private Label lastCommandLabel;

    @FXML
    private Pane moduleContainer;

    private DataStructureModule currentModule;
    private CommandManager commandManager;

    private void loadModule(DataStructureModule module) {
        currentModule = module;
        moduleContainer.getChildren().clear();
        moduleContainer.getChildren().add(module.getModuleUI());
    }

    public MainController() {
        commandManager = new CommandManager();
    }

    @FXML
    private void handleUndo() {}

    @FXML
    private void handleRedo() {}

    public void setUndoAction(EventHandler<ActionEvent> handler) {
        undoButton.setOnAction(handler);
    }

    public void setRedoAction(EventHandler<ActionEvent> handler) {
        redoButton.setOnAction(handler);
    }

    @FXML
    private void handleQuit() {
        Platform.exit();
    }

    public void updateLastCommand(String command) {
        lastCommandLabel.setText(command);
    }

    @FXML
    private void handleLinkedList() {
        loadModule(new LinkedListModule(this, commandManager));
    }

    @FXML
    private void handleHashtable() {
        loadModule(new HashTableModule(this, commandManager));
    }

    @FXML
    private void handleGraph() {
        loadModule(new GraphModule());
    }

    public void handleBinaryTree(ActionEvent actionEvent) {
        loadModule(new BinaryTreeModule(this,commandManager));
    }
}
