package org.conava.dsv.modules.linkedList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import org.conava.dsv.app.MainController;
import org.conava.dsv.commands.Command;
import org.conava.dsv.commands.CommandManager;
import org.conava.dsv.modules.DataStructureModule;

public class LinkedListModule implements DataStructureModule {

    private BorderPane moduleRoot;
    private Label errorLabel;
    TextArea inputArea;
    TextArea outputArea;

    private CustomLinkedList linkedList;
    private CommandManager commandManager;
    MainController mainController;

    // Use a FlowPane for visualization so nodes can wrap onto multiple rows
    private FlowPane visualizationContainer;

    public LinkedListModule(MainController mainController, CommandManager commandManager) {
        this.commandManager = commandManager;
        this.mainController = mainController;
        initializeModuleUI();
        initializeDataStructure();
    }

    private void initializeModuleUI() {
        moduleRoot = new BorderPane();
        moduleRoot.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        moduleRoot.setPrefSize(1300, 720);

        // Left: Command Buttons
        moduleRoot.setLeft(createCommandButtons());

        // Bottom: Input and Output
        moduleRoot.setBottom(createInputOutputArea());

        // Center: Visualization Area to the right of commands and above input/output
        // Using a FlowPane so elements can wrap to new rows if there are many
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

        Button addButton = new Button(".add(Object o)");
        addButton.setOnAction(e -> {
            addCommand(new LinkedList_add(linkedList, inputArea.getText()));
        });

        Button removeButton = new Button(".remove(Object o)");
        removeButton.setOnAction(e -> {
            addCommand(new LinkedList_remove(linkedList, inputArea.getText()));
        });

        Button clearButton = new Button(".clear()");
        clearButton.setOnAction(e -> {
            addCommand(new LinkedList_clear(linkedList));
        });

        Button containsButton = new Button(".contains(Object o)");
        containsButton.setOnAction(e -> {
            addCommand(new LinkedList_contains(linkedList, inputArea.getText()));
        });

        Button sizeButton = new Button(".size()");
        sizeButton.setOnAction(e -> {
            addCommand(new LinkedList_size(linkedList));
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

        commandButtons.getChildren().addAll(addButton, removeButton, clearButton, containsButton, sizeButton);
        return commandButtons;
    }

    private HBox createInputOutputArea() {
        HBox inputOutput = new HBox(10);
        inputOutput.setPadding(new Insets(10));
        inputOutput.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        inputArea = new TextArea();
        inputArea.setPromptText("Input");
        inputArea.setPrefWidth(500);
        outputArea = new TextArea();
        outputArea.setPromptText("Output");
        outputArea.setPrefWidth(500);
        outputArea.setEditable(false);
        errorLabel = new Label();
        errorLabel.setPrefWidth(300);
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setWrapText(true);

        inputOutput.getChildren().addAll(inputArea, outputArea, errorLabel);
        return inputOutput;
    }

    private void initializeDataStructure() {
        linkedList = new CustomLinkedList();
        updateVisualization();
    }

    @Override
    public Node getModuleUI() {
        return moduleRoot;
    }

    private void addCommand(Command command) {
        commandManager.executeCommand(command);
        outputArea.setText(command.getOutput());
        errorLabel.setText(command.getError());
        inputArea.clear();
        mainController.updateLastCommand("Last command: " + command.getString());
        updateVisualization();
    }

    // This method updates the visualization to the right of commands and above input/output.
    // Uses FlowPane to allow wrapping into multiple rows if there are many elements.
    private void updateVisualization() {
        visualizationContainer.getChildren().clear();

        int size = linkedList.size();
        for (int i = 0; i < size; i++) {
            String value = linkedList.get(i);

            // Create a visual node: a rectangle with text
            StackPane nodePane = new StackPane();
            Rectangle rect = new Rectangle(60, 30);
            rect.setStyle("-fx-fill: lightblue; -fx-stroke: black; -fx-stroke-width: 1;");
            Text text = new Text(value);
            nodePane.getChildren().addAll(rect, text);

            visualizationContainer.getChildren().add(nodePane);

            if (i < size - 1) {
                HBox arrowBox = new HBox();
                arrowBox.setPrefSize(60, 30);
                arrowBox.setAlignment(Pos.CENTER); // center horizontally and vertically

                StackPane arrowPane = new StackPane();
                arrowPane.setPrefSize(60, 30);
                arrowPane.setAlignment(Pos.CENTER); // center children inside

                // Draw the line from -10 to 10 horizontally at y=0 (center line)
                Line line = new Line(-10, 0, 10, 0);
                line.setStyle("-fx-stroke: black; -fx-stroke-width: 2;");

                // Arrowhead at the end of the line (10,0)
                Polygon arrowHead = new Polygon(
                        10.0, 0.0,
                        5.0, -5.0,
                        5.0, 5.0
                );
                arrowHead.setStyle("-fx-fill: black;");

                Group arrowGroup = new Group(line, arrowHead);

                // No vertical translation needed since we are relying on center alignment
                arrowPane.getChildren().add(arrowGroup);
                arrowBox.getChildren().add(arrowPane);
                visualizationContainer.getChildren().add(arrowBox);
            }
        }
        }
}
