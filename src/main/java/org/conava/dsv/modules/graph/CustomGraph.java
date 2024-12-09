package org.conava.dsv.modules.graph;

import java.util.*;

/**
 * The CustomGraph class represents a custom graph data structure.
 */
public class CustomGraph {
    public static class Vertex {
        String id;
        Vertex(String id) {
            this.id = id;
        }
    }

    private final boolean directed;
    private final Map<String, Vertex> vertices;
    private final Map<String, Set<String>> adjacency;
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
        Set<String> edgesFrom = adjacency.get(id);
        edgeCount -= edgesFrom.size();
        adjacency.remove(id);

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

    // Get neighbors of a vertex
    public Set<String> getNeighbors(String id) {
        return adjacency.getOrDefault(id, Collections.emptySet());
    }

    // Dijkstra's algorithm for shortest path (assuming unweighted or equal weights = 1)
    // Returns the shortest path from 'start' to 'end' as a list of vertex IDs, or empty if no path.
    public List<String> dijkstra(String start, String end) {
        if (!containsVertex(start) || !containsVertex(end)) return Collections.emptyList();
        if (start.equals(end)) return Collections.singletonList(start);

        // Distances and predecessor maps
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        for (String v : vertices.keySet()) {
            dist.put(v, Integer.MAX_VALUE);
            prev.put(v, null);
        }
        dist.put(start, 0);

        // Priority queue
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        pq.add(start);

        while (!pq.isEmpty()) {
            String u = pq.poll();
            if (u.equals(end)) break; // Found shortest path

            int d = dist.get(u);
            for (String nbr : getNeighbors(u)) {
                int alt = d + 1; // cost = 1 for each edge
                if (alt < dist.get(nbr)) {
                    dist.put(nbr, alt);
                    prev.put(nbr, u);
                    pq.add(nbr);
                }
            }
        }

        // Reconstruct path
        List<String> path = new ArrayList<>();
        String curr = end;
        while (curr != null) {
            path.add(curr);
            curr = prev.get(curr);
        }
        Collections.reverse(path);
        if (path.get(0).equals(start)) {
            return path;
        }
        return Collections.emptyList();
    }
}
