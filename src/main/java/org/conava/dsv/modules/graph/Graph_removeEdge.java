package org.conava.dsv.modules.graph;

import org.conava.dsv.commands.Command;

public class Graph_removeEdge implements Command {
    private final CustomGraph graph;
    private final String from, to;
    private String error = "";
    private String output = "";
    private boolean removed = false;

    public Graph_removeEdge(CustomGraph graph, String from, String to) {
        this.graph = graph;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        if (!graph.containsEdge(from, to)) {
            error = "Edge not found: " + from + " -> " + to;
            return;
        }
        removed = graph.removeEdge(from, to);
        if (removed) {
            output = "Edge removed: " + from + " -> " + to;
            if (!graph.isDirected()) {
                output = "Edge removed (undirected): " + from + " - " + to;
            }
        } else {
            error = "Failed to remove edge: " + from + " -> " + to;
        }
    }

    @Override
    public void undo() {
        if (removed) {
            graph.addEdge(from, to);
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
        return "graph.removeEdge(" + from + "," + to + ")";
    }
}
