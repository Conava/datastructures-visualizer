package org.conava.dsv.modules.graph;

import org.conava.dsv.commands.Command;

public class Graph_addEdge implements Command {
    private final CustomGraph graph;
    private final String from, to;
    private String error = "";
    private String output = "";
    private boolean added = false;

    public Graph_addEdge(CustomGraph graph, String from, String to) {
        this.graph = graph;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        added = graph.addEdge(from, to);
        if (added) {
            output = "Edge added: " + from + " -> " + to;
            if (!graph.isDirected()) {
                output = "Edge added (undirected): " + from + " - " + to;
            }
        } else {
            error = "Failed to add edge (check vertices or edge already exists)";
        }
    }

    @Override
    public void undo() {
        if (added) {
            graph.removeEdge(from, to);
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
        return "graph.add(" + from + "," + to + ")";
    }
}
