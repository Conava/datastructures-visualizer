package org.conava.dsv.modules.linkedList;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.conava.dsv.modules.DataStructureModule;

public class LinkedListModule implements DataStructureModule {

    private BorderPane moduleRoot;
    private Label errorLabel;
    private CustomLinkedList linkedList;

    public LinkedListModule() {
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

        // Center: Visualization Area
        Pane visualizationArea = new Pane();
        visualizationArea.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        moduleRoot.setCenter(visualizationArea);
    }

    private VBox createCommandButtons() {
        VBox commandButtons = new VBox(10);
        commandButtons.setPadding(new Insets(10));
        commandButtons.setPrefWidth(200);
        commandButtons.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        Button addButton = new Button(".add(Object o)");
        Button removeButton = new Button(".remove(Object o)");
        Button clearButton = new Button(".clear()");
        Button containsButton = new Button(".contains(Object o)");
        Button sizeButton = new Button(".size()");

        commandButtons.getChildren().addAll(addButton, removeButton, clearButton, containsButton, sizeButton);
        return commandButtons;
    }

    private HBox createInputOutputArea() {
        HBox inputOutput = new HBox(10);
        inputOutput.setPadding(new Insets(10));
        inputOutput.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        TextArea inputArea = new TextArea();
        inputArea.setPromptText("Input");
        inputArea.setPrefWidth(500);
        TextArea outputArea = new TextArea();
        outputArea.setPromptText("Output");
        outputArea.setPrefWidth(500);
        outputArea.setEditable(false);
        errorLabel = new Label();
        errorLabel.setPrefWidth(300);
        errorLabel.setStyle("-fx-text-fill: red;");

        inputOutput.getChildren().addAll(inputArea, outputArea, errorLabel);
        return inputOutput;
    }

    private void initializeDataStructure() {
        linkedList = new CustomLinkedList();
    }

    @Override
    public Pane getView() {
        return null;
    }

    @Override
    public void executeCommand(String command) {

    }

    @Override
    public Node getModuleUI() {
        return moduleRoot;
    }

    private void showError(String errorMessage) {
        errorLabel.setText(errorMessage);
    }

    private void clearError() {
        errorLabel.setText("");
    }
}