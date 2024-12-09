package org.conava.dsv.modules.graph;

import org.conava.dsv.commands.Command;

public class Graph_containsEdge implements Command {
    private final CustomGraph graph;
    private final String from, to;
    private String error = "";
    private String output = "";

    public Graph_containsEdge(CustomGraph graph, String from, String to) {
        this.graph = graph;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        boolean found = graph.containsEdge(from, to);
        output = "containsEdge(" + from + "," + to + ") = " + found;
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
        return "Graph_containsEdge(" + from + "," + to + ")";
    }
}
