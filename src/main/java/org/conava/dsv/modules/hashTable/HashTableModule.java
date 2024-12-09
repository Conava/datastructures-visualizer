package org.conava.dsv.modules.hashTable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import org.conava.dsv.app.MainController;
import org.conava.dsv.commands.Command;
import org.conava.dsv.commands.CommandManager;
import org.conava.dsv.modules.DataStructureModule;

import java.util.LinkedList;

public class HashTableModule implements DataStructureModule {
    private BorderPane moduleRoot;
    private Label errorLabel;
    private TextArea inputAreaKey;
    private TextArea inputAreaValue;
    private TextArea outputArea;

    private CustomHashTable hashTable;
    private final CommandManager commandManager;
    private final MainController mainController;

    private FlowPane visualizationContainer;

    public HashTableModule(MainController mainController, CommandManager commandManager) {
        this.commandManager = commandManager;
        this.mainController = mainController;
        initializeModuleUI();
        initializeDataStructure();
    }

    private void initializeModuleUI() {
        moduleRoot = new BorderPane();
        moduleRoot.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        moduleRoot.setPrefSize(1300, 720);

        moduleRoot.setLeft(createCommandButtons());
        moduleRoot.setBottom(createInputOutputArea());

        visualizationContainer = new FlowPane();
        visualizationContainer.setPadding(new Insets(10));
        visualizationContainer.setHgap(10);
        visualizationContainer.setVgap(10);
        visualizationContainer.setAlignment(Pos.TOP_LEFT);
        visualizationContainer.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        moduleRoot.setCenter(visualizationContainer);
    }

    private VBox createCommandButtons() {
        VBox commandButtons = new VBox(10);
        commandButtons.setPadding(new Insets(10));
        commandButtons.setPrefWidth(200);
        commandButtons.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        Button addButton = new Button(".add(key, value)");
        addButton.setOnAction(e -> {
            String key = inputAreaKey.getText();
            String value = inputAreaValue.getText();
            addCommand(new HashTable_add(hashTable, key, value));
        });

        Button removeButton = new Button(".remove(key)");
        removeButton.setOnAction(e -> {
            addCommand(new HashTable_remove(hashTable, inputAreaKey.getText()));
        });

        Button getButton = new Button(".get(key)");
        getButton.setOnAction(e -> {
            addCommand(new HashTable_get(hashTable, inputAreaKey.getText()));
        });

        Button containsButton = new Button(".containsKey(key)");
        containsButton.setOnAction(e -> {
            addCommand(new HashTable_containsKey(hashTable, inputAreaKey.getText()));
        });

        Button clearButton = new Button(".clear()");
        clearButton.setOnAction(e -> {
            addCommand(new HashTable_clear(hashTable));
        });

        Button sizeButton = new Button(".size()");
        sizeButton.setOnAction(e -> {
            addCommand(new HashTable_size(hashTable));
        });

        mainController.setUndoAction(e -> {
            commandManager.undo();
            mainController.updateLastCommand("Last command: " + commandManager.getLastCommandString());
            updateVisualization();
        });

        mainController.setRedoAction(e -> {
            commandManager.redo();
            mainController.updateLastCommand("Last command: " + commandManager.getLastCommandString());
            updateVisualization();
        });

        commandButtons.getChildren().addAll(addButton, removeButton, getButton, containsButton, clearButton, sizeButton);
        return commandButtons;
    }

    private HBox createInputOutputArea() {
        HBox inputOutput = new HBox(10);
        inputOutput.setPadding(new Insets(10));
        inputOutput.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        inputAreaKey = new TextArea();
        inputAreaKey.setPromptText("Key");
        inputAreaKey.setPrefWidth(250);

        inputAreaValue = new TextArea();
        inputAreaValue.setPromptText("Value");
        inputAreaValue.setPrefWidth(250);

        outputArea = new TextArea();
        outputArea.setPromptText("Output");
        outputArea.setPrefWidth(500);
        outputArea.setEditable(false);

        errorLabel = new Label();
        errorLabel.setPrefWidth(300);
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setWrapText(true);

        inputOutput.getChildren().addAll(inputAreaKey, inputAreaValue, outputArea, errorLabel);
        return inputOutput;
    }

    private void initializeDataStructure() {
        hashTable = new CustomHashTable();
        updateVisualization();
    }

    @Override
    public Pane getView() {
        return null;
    }

    @Override
    public Node getModuleUI() {
        return moduleRoot;
    }

    private void addCommand(Command command) {
        commandManager.executeCommand(command);
        outputArea.setText(command.getOutput());
        errorLabel.setText(command.getError());
        inputAreaKey.clear();
        inputAreaValue.clear();
        mainController.updateLastCommand("Last command: " + command.getString());
        updateVisualization();
    }

    private void updateVisualization() {
        visualizationContainer.getChildren().clear();

        // Visualize the hash table as a series of buckets, each bucket is a vertical column
        // containing key-value pairs.

        int capacity = hashTable.capacity();
        var buckets = hashTable.getBuckets();

        for (int i = 0; i < capacity; i++) {
            VBox bucketBox = new VBox(5);
            bucketBox.setAlignment(Pos.TOP_CENTER);
            bucketBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-padding: 5;");

            Text bucketLabel = new Text("Bucket " + i);
            bucketBox.getChildren().add(bucketLabel);

            LinkedList<CustomHashTable.Entry> bucket = buckets.get(i);
            if (bucket.isEmpty()) {
                Text emptyText = new Text("(empty)");
                bucketBox.getChildren().add(emptyText);
            } else {
                for (CustomHashTable.Entry entry : bucket) {
                    // Represent each entry as a rectangle with text: "key=value"
                    StackPane entryPane = new StackPane();
                    Rectangle rect = new Rectangle(100, 30);
                    rect.setStyle("-fx-fill: lightblue; -fx-stroke: black; -fx-stroke-width: 1;");
                    Text text = new Text(entry.key + "=" + entry.value);
                    entryPane.getChildren().addAll(rect, text);
                    bucketBox.getChildren().add(entryPane);
                }
            }

            visualizationContainer.getChildren().add(bucketBox);
        }
    }
}