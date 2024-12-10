package org.conava.dsv.modules.graph;

import org.conava.dsv.commands.Command;

public class Graph_edgeCount implements Command {
    private final CustomGraph graph;
    private String error = "";
    private String output = "";

    public Graph_edgeCount(CustomGraph graph) {
        this.graph = graph;
    }

    @Override
    public void execute() {
        int count = graph.edgeCount();
        output = "Edge count: " + count;
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
        return "graph.edgeCount()";
    }
}
