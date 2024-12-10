package org.conava.dsv.modules.binaryTree;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import org.conava.dsv.app.MainController;
import org.conava.dsv.commands.Command;
import org.conava.dsv.commands.CommandManager;
import org.conava.dsv.modules.DataStructureModule;

/**
 * The BinaryTreeModule class is a module for visualizing a binary tree.
 * It provides a UI for adding, removing, and checking if a value exists in the binary tree.
 */
public class BinaryTreeModule implements DataStructureModule {
    private BorderPane moduleRoot;
    private Label errorLabel;
    private TextArea inputArea;
    private TextArea outputArea;

    private CustomBinaryTree binaryTree;
    private final CommandManager commandManager;
    private final MainController mainController;

    private Pane visualizationArea;

    public BinaryTreeModule(MainController mainController, CommandManager commandManager) {
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

        visualizationArea = new Pane();
        visualizationArea.setPadding(new Insets(20));
        visualizationArea.setStyle("-fx-border-color: gray; -fx-border-width: 1;");
        moduleRoot.setCenter(visualizationArea);
    }

    private VBox createCommandButtons() {
        VBox commandButtons = new VBox(10);
        commandButtons.setPadding(new Insets(10));
        commandButtons.setPrefWidth(200);
        commandButtons.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        Button addButton = new Button(".add(value)");
        addButton.setOnAction(e -> {
            addCommand(new BinaryTree_add(binaryTree, inputArea.getText()));
        });

        Button removeButton = new Button(".remove(value)");
        removeButton.setOnAction(e -> {
            addCommand(new BinaryTree_remove(binaryTree, inputArea.getText()));
        });

        Button containsButton = new Button(".contains(value)");
        containsButton.setOnAction(e -> {
            addCommand(new BinaryTree_contains(binaryTree, inputArea.getText()));
        });

        Button clearButton = new Button(".clear()");
        clearButton.setOnAction(e -> {
            addCommand(new BinaryTree_clear(binaryTree));
        });

        Button sizeButton = new Button(".size()");
        sizeButton.setOnAction(e -> {
            addCommand(new BinaryTree_size(binaryTree));
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

        commandButtons.getChildren().addAll(addButton, removeButton, containsButton, clearButton, sizeButton);
        return commandButtons;
    }

    private HBox createInputOutputArea() {
        HBox inputOutput = new HBox(10);
        inputOutput.setPadding(new Insets(10));
        inputOutput.setStyle("-fx-border-color: gray; -fx-border-width: 1;");

        inputArea = new TextArea();
        inputArea.setPromptText("Value");
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
        binaryTree = new CustomBinaryTree();
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

    private void updateVisualization() {
        visualizationArea.getChildren().clear();

        // Visualize the binary tree in a hierarchical manner.
        // We'll do a simple layout:
        // Calculate x positions with a fixed horizontal gap and vertical gap.

        double startX = visualizationArea.getWidth() / 2;
        double startY = 50;
        drawNode(binaryTree.getRoot(), startX, startY, visualizationArea.getWidth() / 4);
    }

    //todo: Make it fit the canvas and not overflow
    private void drawNode(CustomBinaryTree.Node node, double x, double y, double xOffset) {
        if (node == null) return;

        // Draw node
        Circle circle = new Circle(x, y, 20);
        circle.setStyle("-fx-fill: lightblue; -fx-stroke: black; -fx-stroke-width: 1;");
        Text text = new Text(node.value);
        text.setX(x - text.getLayoutBounds().getWidth()/2);
        text.setY(y + 5); // center text vertically

        visualizationArea.getChildren().addAll(circle, text);

        // Draw edges and recursive calls
        double childY = y + 80; // vertical gap
        if (node.left != null) {
            Line leftLine = new Line(x, y+20, x - xOffset, childY-20);
            visualizationArea.getChildren().add(leftLine);
            drawNode(node.left, x - xOffset, childY, xOffset/2);
        }
        if (node.right != null) {
            Line rightLine = new Line(x, y+20, x + xOffset, childY-20);
            visualizationArea.getChildren().add(rightLine);
            drawNode(node.right, x + xOffset, childY, xOffset/2);
        }
    }
}
