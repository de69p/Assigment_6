package org.example.problem1;

import java.util.*;

public class DFS {
    private static final HashMap<String, List<String>> graph = new HashMap<>();
    private static final Set<String> visited = new HashSet<>();

    public static void main(String[] args) {
        // Initialize the graph
        graph.put("A", new ArrayList<>());
        graph.put("B", List.of("A", "C"));
        graph.put("C", List.of("E", "F"));
        graph.put("D", List.of("G", "A"));
        graph.put("E", List.of("D", "B", "F"));
        graph.put("F", new ArrayList<>());
        graph.put("G", List.of("E", "F"));

        dfs("G");
    }

    public static void dfs(String node) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        System.out.println(node);

        List<String> neighbors = new ArrayList<>(graph.get(node));  // Create a mutable copy of the list
        Collections.sort(neighbors);  // Now you can sort it

        for (String neighbor : neighbors) {
            dfs(neighbor);
        }
    }

}

