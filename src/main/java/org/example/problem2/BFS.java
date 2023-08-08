package org.example.problem2;

import java.util.*;

class BFS {
    // Define the Graph using an Adjacency List
    private static final HashMap<String, LinkedList<String>> graph = new HashMap<>();

    public static void main(String[] args) {
        // Initialize the Graph
        addEdge("A", "B");
        addEdge("A", "C");
        addEdge("B", "A");
        addEdge("B", "E");
        addEdge("B", "D");
        addEdge("D", "B");
        addEdge("D", "E");
        addEdge("C", "A");
        addEdge("C", "E");
        addEdge("C", "G");
        addEdge("E", "C");
        addEdge("E", "B");
        addEdge("E", "D");
        addEdge("E", "F");
        addEdge("E", "H");
        addEdge("F", "E");
        addEdge("F", "H");
        addEdge("F", "I");
        addEdge("G", "C");
        addEdge("G", "H");
        addEdge("H", "G");
        addEdge("H", "E");
        addEdge("H", "F");
        addEdge("H", "I");
        addEdge("I", "F");
        addEdge("I", "H");

        // Perform BFS
        bfs();
    }

    private static void addEdge(String node1, String node2) {
        LinkedList<String> adjacent = graph.computeIfAbsent(node1, k -> new LinkedList<>());
        adjacent.add(node2);
    }

    private static void bfs() {
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add("I");
        visited.add("I");
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            System.out.print(vertex + ", ");
            for (String v : graph.get(vertex)) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
    }
}
