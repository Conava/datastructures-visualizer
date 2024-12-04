package org.conava.dsv.modules;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LinkedListModule implements DataStructureModule {

    private BorderPane moduleRoot;
    private Label errorLabel;

    public LinkedListModule() {
        initializeModuleUI();
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

        Button addNodeButton = new Button("Add Node");
        Button deleteNodeButton = new Button("Delete Node");
        // Add event handlers for buttons as needed

        commandButtons.getChildren().addAll(addNodeButton, deleteNodeButton);
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