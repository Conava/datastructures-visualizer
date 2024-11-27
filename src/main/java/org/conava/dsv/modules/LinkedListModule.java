package org.conava.dsv.modules;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LinkedListModule implements DataStructureModule {

    private BorderPane moduleRoot;
    private Pane visualizationArea;
    private TextArea inputArea;
    private TextArea outputArea;

    public LinkedListModule() {
        initializeModuleUI();
    }

    private void initializeModuleUI() {
        moduleRoot = new BorderPane();
        moduleRoot.setPrefSize(1300, 700); // Adjusted to fit within main window

        // Left: Command Buttons
        VBox commandButtons = new VBox(10);
        commandButtons.setPadding(new Insets(10));
        commandButtons.setPrefWidth(200);
        commandButtons.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        Button addNodeButton = new Button("Add Node");
        Button deleteNodeButton = new Button("Delete Node");
        // Add event handlers for buttons as needed

        commandButtons.getChildren().addAll(addNodeButton, deleteNodeButton);
        moduleRoot.setLeft(commandButtons);

        // Bottom: Input and Output
        HBox inputOutput = new HBox(10);
        inputOutput.setPadding(new Insets(10));
        inputOutput.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        inputArea = new TextArea();
        inputArea.setPromptText("Input");
        inputArea.setPrefWidth(600);
        outputArea = new TextArea();
        outputArea.setPromptText("Output");
        outputArea.setPrefWidth(600);
        outputArea.setEditable(false);

        inputOutput.getChildren().addAll(inputArea, outputArea);
        moduleRoot.setBottom(inputOutput);

        // Center: Visualization Area
        visualizationArea = new Pane();
        visualizationArea.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        moduleRoot.setCenter(visualizationArea);
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
}
