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

import java.util.Optional;

public class MainController {

    @FXML
    private Button undoButton;

    @FXML
    private Button redoButton;

    @FXML
    private Label lastCommandLabel;

    @FXML
    private Pane moduleContainer;

    private LinkedListModule linkedListModule;
    private HashTableModule hashTableModule;
    private GraphModule graphModule;
    private BinaryTreeModule binaryTreeModule;

    private CommandManager commandManager;

    private void loadModule(DataStructureModule module) {
        moduleContainer.getChildren().clear();
        moduleContainer.getChildren().add(module.getModuleUI());
    }

    public MainController() {
        commandManager = new CommandManager();
    }

    @FXML
    private void handleUndo() {
    }

    @FXML
    private void handleRedo() {
    }

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
        if (linkedListModule == null) {
            linkedListModule = new LinkedListModule(this, commandManager);
        }
        loadModule(linkedListModule);
    }

    @FXML
    private void handleHashtable() {
        if (hashTableModule == null) {
            hashTableModule = new HashTableModule(this, commandManager);
        }
        loadModule(hashTableModule);
    }

    @FXML
    private void handleGraph() {
        if (graphModule == null) {
            configureGraph();
        }
        loadModule(graphModule);

    }

    private void configureGraph() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Graph Module Configuration");
        alert.setHeaderText("Choose the graph type");
        alert.setContentText("Is the graph directed?");

        ButtonType buttonYes = new ButtonType("Yes");
        ButtonType buttonNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);

        // Show the dialog and capture the result
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(buttonType -> {
            boolean isDirected = buttonType == buttonYes;
            // Pass the boolean value to the GraphModule constructor
            graphModule = new GraphModule(this, commandManager, isDirected);
        });
    }

    public void handleBinaryTree(ActionEvent actionEvent) {
        if (binaryTreeModule == null) {
            binaryTreeModule = new BinaryTreeModule(this, commandManager);
        }
        loadModule(binaryTreeModule);
    }
}
