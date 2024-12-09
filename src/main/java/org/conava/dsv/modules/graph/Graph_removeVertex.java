package org.conava.dsv.modules.graph;

import org.conava.dsv.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class Graph_removeVertex implements Command {
    private final CustomGraph graph;
    private final String id;
    private String error = "";
    private String output = "";
    private boolean removed = false;

    // For undo, store graph state related to this vertex
    private List<String> oldVertices = new ArrayList<>(); // just the removed one
    private List<String[]> oldEdges = new ArrayList<>();

    public Graph_removeVertex(CustomGraph graph, String id) {
        this.graph = graph;
        this.id = id;
    }

    @Override
    public void execute() {
        if (!graph.containsVertex(id)) {
            error = "Vertex not found: " + id;
            return;
        }

        // Store vertex
        oldVertices.add(id);

        // Store all edges involving this vertex
        var adjacency = graph.getAdjacency();
        for (var from : adjacency.keySet()) {
            for (var to : adjacency.get(from)) {
                if (from.equals(id) || to.equals(id)) {
                    oldEdges.add(new String[]{from, to});
                }
            }
        }

        removed = graph.removeVertex(id);
        if (removed) {
            output = "Vertex removed: " + id;
        } else {
            error = "Failed to remove vertex: " + id;
        }
    }

    @Override
    public void undo() {
        if (removed) {
            // Re-add vertex
            for (String v : oldVertices) {
                graph.addVertex(v);
            }
            // Re-add edges
            for (String[] edge : oldEdges) {
                graph.addEdge(edge[0], edge[1]);
            }
        }
    }

    @Override
    public String getOutput() {
        return output;
    }

    @Override
    public String getError() {
        return error;
    }

    @Override
    public String getString() {
        return "Graph_removeVertex(" + id + ")";
    }
}
