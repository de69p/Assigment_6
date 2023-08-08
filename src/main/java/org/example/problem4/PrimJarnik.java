package org.example.problem4;

import java.util.*;

class Edge implements Comparable<Edge> {
    int dest;
    int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class PrimJarnik {
    private final int V;
    private final LinkedList<Edge>[] graph;
    private final Map<Character, Integer> map;

    public PrimJarnik(int vertices) {
        this.V = vertices;
        graph = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            graph[i] = new LinkedList<>();
        }

        map = new HashMap<>();
        char c = 'a';
        for (int i = 0; i < vertices; i++) {
            map.put(c++, i);
        }
    }

    public void addEdge(char src, char dest, int weight) {
        int srcIndex = map.get(src);
        int destIndex = map.get(dest);

        graph[srcIndex].add(new Edge(destIndex, weight));
        graph[destIndex].add(new Edge(srcIndex, weight));
    }

    public void findMST() {
        boolean[] visited = new boolean[V];
        int[] dist = new int[V];
        int[] pred = new int[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
        dist[0] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));

        System.out.println("Sequence of nodes in the cloud:");
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.dest;

            if (visited[u]) continue;

            visited[u] = true;
            System.out.print((char)(u + 'a') + " ");

            for (Edge edge : graph[u]) {
                if (!visited[edge.dest] && edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = edge.weight;
                    pred[edge.dest] = u;
                    pq.add(new Edge(edge.dest, dist[edge.dest]));
                }
            }
        }

        System.out.println("\nMinimum Spanning Tree (edges):");
        for (int i = 1; i < V; i++) {
            System.out.println("(" + (char)(pred[i] + 'a') + ", " + (char)(i + 'a') + ")");
        }
    }

    public static void main(String[] args) {
        PrimJarnik g = new PrimJarnik(7);
        g.addEdge('a', 'b', 19);
        g.addEdge('a', 'c', 12);
        g.addEdge('b', 'c', 5);
        g.addEdge('b', 'd', 6);
        g.addEdge('b', 'e', 15);
        g.addEdge('c', 'd', 9);
        g.addEdge('c', 'e', 17);
        g.addEdge('d', 'f', 8);
        g.addEdge('d', 'g', 7);
        g.addEdge('e', 'f', 4);
        g.addEdge('e', 'g', 20);
        g.addEdge('f', 'g', 10);

        g.findMST();
    }
}

