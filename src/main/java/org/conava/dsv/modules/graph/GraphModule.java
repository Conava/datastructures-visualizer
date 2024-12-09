package org.conava.dsv.modules.graph;

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

import java.util.*;

public class GraphModule implements DataStructureModule {

    private BorderPane moduleRoot;
    private Label errorLabel;
    private TextArea inputArea;
    private TextArea outputArea;

    private final CustomGraph graph;
    private final CommandManager commandManager;
    private final MainController mainController;

    private Pane visualizationArea;

    public GraphModule(MainController mainController, CommandManager commandManager, boolean directed) {
        this.commandManager = commandManager;
        this.mainController = mainController;
        this.graph = new CustomGraph(directed);
        initializeModuleUI();
        updateVisualization();
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

        Button addVertexButton = new Button(".addVertex(id)");
        addVertexButton.setOnAction(e -> {
            String id = inputArea.getText().trim();
            addCommand(new Graph_addVertex(graph, id));
        });

        Button removeVertexButton = new Button(".removeVertex(id)");
        removeVertexButton.setOnAction(e -> {
            String id = inputArea.getText().trim();
            addCommand(new Graph_removeVertex(graph, id));
        });

        Button addEdgeButton = new Button(".addEdge(from,to)");
        addEdgeButton.setOnAction(e -> {
            String[] parts = inputArea.getText().split("\\s+");
            if (parts.length == 2) {
                addCommand(new Graph_addEdge(graph, parts[0], parts[1]));
            } else {
                errorLabel.setText("Expected input: from to");
            }
        });

        Button removeEdgeButton = new Button(".removeEdge(from,to)");
        removeEdgeButton.setOnAction(e -> {
            String[] parts = inputArea.getText().split("\\s+");
            if (parts.length == 2) {
                addCommand(new Graph_removeEdge(graph, parts[0], parts[1]));
            } else {
                errorLabel.setText("Expected input: from to");
            }
        });

        Button containsVertexButton = new Button(".containsVertex(id)");
        containsVertexButton.setOnAction(e -> {
            String id = inputArea.getText().trim();
            addCommand(new Graph_containsVertex(graph, id));
        });

        Button containsEdgeButton = new Button(".containsEdge(from,to)");
        containsEdgeButton.setOnAction(e -> {
            String[] parts = inputArea.getText().split("\\s+");
            if (parts.length == 2) {
                addCommand(new Graph_containsEdge(graph, parts[0], parts[1]));
            } else {
                errorLabel.setText("Expected input: from to");
            }
        });

        Button sizeButton = new Button(".vertexCount()");
        sizeButton.setOnAction(e -> {
            addCommand(new Graph_vertexCount(graph));
        });

        Button edgeCountButton = new Button(".edgeCount()");
        edgeCountButton.setOnAction(e -> {
            addCommand(new Graph_edgeCount(graph));
        });

        Button clearButton = new Button(".clear()");
        clearButton.setOnAction(e -> {
            addCommand(new Graph_clear(graph));
        });

        mainController.setUndoAction(e -> {
            commandManager.undo();
            mainController.updateLastCommand("Last command: " + commandManager.getLastCommandString());
            updateVisualization();
        });

        // No redo as requested

        commandButtons.getChildren().addAll(
                addVertexButton, removeVertexButton, addEdgeButton, removeEdgeButton,
                containsVertexButton, containsEdgeButton, sizeButton, edgeCountButton, clearButton
        );
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

    @Override
    public Node getModuleUI() {
        return moduleRoot;
    }

    @Override
    public Pane getView() {
        return null;
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

        // We'll use a simple layout algorithm to place vertices automatically:
        // Place all vertices evenly spaced in a circle.
        Collection<CustomGraph.Vertex> verts = graph.getVertices();
        int n = verts.size();
        if (n == 0) return;

        double width = visualizationArea.getWidth();
        double height = visualizationArea.getHeight();

        double radius = Math.min(width, height) / 3;
        double centerX = width / 2;
        double centerY = height / 2;

        List<CustomGraph.Vertex> vertexList = new ArrayList<>(verts);
        Map<String, double[]> positions = new HashMap<>();

        for (int i = 0; i < n; i++) {
            double angle = 2 * Math.PI * i / n;
            double x = centerX + radius * Math.cos(angle);
            double y = centerY + radius * Math.sin(angle);
            positions.put(vertexList.get(i).id, new double[]{x, y});
        }

        // Draw edges first
        var adjacency = graph.getAdjacency();
        for (var from : adjacency.keySet()) {
            double[] fromPos = positions.get(from);
            for (var to : adjacency.get(from)) {
                double[] toPos = positions.get(to);
                Line line = new Line(fromPos[0], fromPos[1], toPos[0], toPos[1]);
                line.setStrokeWidth(2);
                visualizationArea.getChildren().add(line);

                if (graph.isDirected()) {
                    //Draw arrowheads
                    double nodeRadius = 15;
                    double dx = toPos[0] - fromPos[0];
                    double dy = toPos[1] - fromPos[1];
                    double angle = Math.atan2(dy, dx);
                    double arrowLength = 10;
                    double arrowAngle = Math.toRadians(20);

                    // Shift the arrow tip back by the node radius
                    double tipX = toPos[0] - nodeRadius * Math.cos(angle);
                    double tipY = toPos[1] - nodeRadius * Math.sin(angle);

                    double x1 = tipX - arrowLength * Math.cos(angle - arrowAngle);
                    double y1 = tipY - arrowLength * Math.sin(angle - arrowAngle);

                    double x2 = tipX - arrowLength * Math.cos(angle + arrowAngle);
                    double y2 = tipY - arrowLength * Math.sin(angle + arrowAngle);

                    Polygon arrowHead = new Polygon(tipX, tipY, x1, y1, x2, y2);
                    arrowHead.setStyle("-fx-fill: black;");
                    visualizationArea.getChildren().add(arrowHead);

                }
            }
        }

        for (var v : vertexList) {
            double[] pos = positions.get(v.id);
            Circle circle = new Circle(pos[0], pos[1], 15);
            circle.setStyle("-fx-fill: lightblue; -fx-stroke: black; -fx-stroke-width: 1;");
            Text text = new Text(v.id);
            text.setX(pos[0] - text.getLayoutBounds().getWidth() / 2);
            text.setY(pos[1] + 5);

            visualizationArea.getChildren().addAll(circle, text);
        }
    }
}