package org.conava.dsv.modules.graph;

import org.conava.dsv.commands.Command;

public class Graph_containsVertex implements Command {
    private final CustomGraph graph;
    private final String id;
    private String error = "";
    private String output = "";

    public Graph_containsVertex(CustomGraph graph, String id) {
        this.graph = graph;
        this.id = id;
    }

    @Override
    public void execute() {
        boolean found = graph.containsVertex(id);
        output = "containsVertex(" + id + ") = " + found;
    }

    @Override
    public void undo() {
        // does not change state
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
        return "Graph_containsVertex(" + id + ")";
    }
}
