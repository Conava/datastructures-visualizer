package org.conava.dsv.modules.graph;

import org.conava.dsv.commands.Command;

public class Graph_addVertex implements Command {
    private final CustomGraph graph;
    private final String id;
    private String error = "";
    private String output = "";
    private boolean added = false;

    public Graph_addVertex(CustomGraph graph, String id) {
        this.graph = graph;
        this.id = id;
    }

    @Override
    public void execute() {
        if (id == null || id.isEmpty()) {
            error = "Vertex ID cannot be empty.";
            return;
        }
        added = graph.addVertex(id);
        if (added) {
            output = "Vertex added: " + id;
        } else {
            error = "Failed to add vertex (maybe ID exists)";
        }
    }

    @Override
    public void undo() {
        if (added) {
            graph.removeVertex(id);
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
        return "Graph_addVertex(" + id + ")";
    }
}
