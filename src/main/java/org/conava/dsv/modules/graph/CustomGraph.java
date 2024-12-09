package org.conava.dsv.modules.graph;

import java.util.*;

/**
 * A simple graph implementation that can be either directed or undirected.
 * Vertices are identified by a unique ID (string). Coordinates are not stored here.
 * The layout (coordinates) will be determined in the module's visualization step.
 */
public class CustomGraph {
    public static class Vertex {
        String id;
        Vertex(String id) {
            this.id = id;
        }
    }

    private boolean directed;
    private Map<String, Vertex> vertices;
    private Map<String, Set<String>> adjacency;
    private int edgeCount;

    public CustomGraph(boolean directed) {
        this.directed = directed;
        vertices = new HashMap<>();
        adjacency = new HashMap<>();
        edgeCount = 0;
    }

    public boolean isDirected() {
        return directed;
    }

    public boolean addVertex(String id) {
        if (id == null || id.isEmpty() || vertices.containsKey(id)) {
            return false;
        }
        Vertex v = new Vertex(id);
        vertices.put(id, v);
        adjacency.put(id, new HashSet<>());
        return true;
    }

    public boolean removeVertex(String id) {
        if (!vertices.containsKey(id)) {
            return false;
        }
        // Remove all edges connected to this vertex
        Set<String> edgesFrom = adjacency.get(id);
        edgeCount -= edgesFrom.size();
        adjacency.remove(id);

        // Remove from all adjacency sets
        for (Set<String> nbrs : adjacency.values()) {
            if (nbrs.remove(id)) {
                edgeCount--;
            }
        }

        vertices.remove(id);
        return true;
    }

    public boolean addEdge(String from, String to) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            return false;
        }
        Set<String> fromSet = adjacency.get(from);
        if (fromSet.contains(to)) {
            // Edge already exists
            return false;
        }
        fromSet.add(to);
        edgeCount++;
        if (!directed) {
            adjacency.get(to).add(from);
            edgeCount++;
        }
        return true;
    }

    public boolean removeEdge(String from, String to) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            return false;
        }
        Set<String> fromSet = adjacency.get(from);
        if (!fromSet.contains(to)) {
            return false;
        }
        fromSet.remove(to);
        edgeCount--;
        if (!directed) {
            adjacency.get(to).remove(from);
            edgeCount--;
        }
        return true;
    }

    public boolean containsVertex(String id) {
        return vertices.containsKey(id);
    }

    public boolean containsEdge(String from, String to) {
        if (!vertices.containsKey(from)) return false;
        return adjacency.get(from).contains(to);
    }

    public int vertexCount() {
        return vertices.size();
    }

    public int edgeCount() {
        return directed ? edgeCount : edgeCount/2;
    }

    public void clear() {
        vertices.clear();
        adjacency.clear();
        edgeCount = 0;
    }

    public Collection<Vertex> getVertices() {
        return vertices.values();
    }

    public Map<String, Set<String>> getAdjacency() {
        return adjacency;
    }
}
