package org.conava.dsv.modules.graph;

import org.conava.dsv.commands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Graph_clear implements Command {
    private final CustomGraph graph;
    private String error = "";
    private String output = "";
    // To undo, store all vertices and edges
    private List<String> oldVertices = new ArrayList<>();
    private List<String[]> oldEdges = new ArrayList<>();

    public Graph_clear(CustomGraph graph) {
        this.graph = graph;
    }

    @Override
    public void execute() {
        // Store all vertices
        for (var v : graph.getVertices()) {
            oldVertices.add(v.id);
        }
        // Store all edges
        var adjacency = graph.getAdjacency();
        for (var from : adjacency.keySet()) {
            for (var to : adjacency.get(from)) {
                oldEdges.add(new String[]{from, to});
            }
        }

        graph.clear();
        output = "Graph cleared";
    }

    @Override
    public void undo() {
        // Rebuild graph
        for (String v : oldVertices) {
            graph.addVertex(v);
        }
        for (String[] edge : oldEdges) {
            graph.addEdge(edge[0], edge[1]);
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
        return "Graph_clear()";
    }
}
