package org.conava.dsv.modules.graph;

import org.conava.dsv.commands.Command;

import java.util.List;

public class Graph_findPath implements Command {
    private final CustomGraph graph;
    private final String from, to;
    private String error = "";
    private String output = "";

    public Graph_findPath(CustomGraph graph, String from, String to) {
        this.graph = graph;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        if (from == null || to == null || from.isEmpty() || to.isEmpty()) {
            error = "Both 'from' and 'to' must be provided.";
            return;
        }
        if (!graph.containsVertex(from) || !graph.containsVertex(to)) {
            error = "One or both vertices not found.";
            return;
        }
        List<String> path = graph.dijkstra(from, to);
        if (path.isEmpty()) {
            output = "No path found between " + from + " and " + to;
        } else {
            output = "Path: " + String.join(" -> ", path);
        }
    }

    @Override
    public void undo() {
        // No changes to the graph, so nothing to undo
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
        return "graph.findPath(" + from + "," + to + ")";
    }
}
