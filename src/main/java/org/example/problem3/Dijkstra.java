package org.example.problem3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public static void computePaths(Node source) {
        source.shortestDistance = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            Node u = queue.poll();

            // Visit each edge exiting u
            for (Edge e : u.adjacencies) {
                Node v = e.target;
                double weight = e.weight;
                double distanceThroughU = u.shortestDistance + weight;
                if (distanceThroughU < v.shortestDistance) {
                    queue.remove(v);
                    v.shortestDistance = distanceThroughU;
                    v.previous = u;
                    queue.add(v);
                }
            }
        }
    }

    public static List<Node> getShortestPathTo(Node target) {
        List<Node> path = new ArrayList<Node>();
        for (Node node = target; node != null; node = node.previous)
            path.add(node);
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Node S = new Node("S");
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");

        S.adjacencies = new Edge[]{new Edge(a, 16), new Edge(b, 5), new Edge(c, 12)};
        a.adjacencies = new Edge[]{};
        b.adjacencies = new Edge[]{new Edge(a, 3), new Edge(c, 5), new Edge(d, 4)};
        c.adjacencies = new Edge[]{new Edge(e, 2), new Edge(d, 6)};
        d.adjacencies = new Edge[]{new Edge(a, 6)};
        e.adjacencies = new Edge[]{new Edge(d, 9)};

        Node[] nodes = {S, a, b, c, d, e};
        computePaths(S);
        for (Node n : nodes) {
            System.out.println("Distance to " + n + ": " + n.shortestDistance);
            List<Node> path = getShortestPathTo(n);
            System.out.println("Path: " + path);
        }
    }
}