import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalsAlgorithm {

    public static int kruskalMST(int V, List<Edge> edges) {
        // Step 1: Sort all edges in non-decreasing order of weight
        Collections.sort(edges);

        // Step 2: Initialize DSU structure
        DisjointSet dsu = new DisjointSet(V);

        int mstWeight = 0;
        int edgesCount = 0;

        // Step 3: Iterate through sorted edges and apply greedy selection
        for (Edge edge : edges) {
            int u = edge.src;
            int v = edge.dest;
            int wt = edge.weight;

            // Step 4: Union check to prevent cycle formation
            if (dsu.unionBySize(u, v)) {
                mstWeight += wt;
                edgesCount++;

                // Optional Optimization: Stop early when V - 1 edges are included
                if (edgesCount == V - 1) break;
            }
        }

        return mstWeight;
    }

    public static void main(String[] args) {
        int V = 5;
        List<Edge> edges = new ArrayList<>();

        // Seeding weighted undirected graph edges: Edge(u, v, weight)
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 2, 1));
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(2, 3, 2));
        edges.add(new Edge(3, 4, 1));
        edges.add(new Edge(2, 4, 2));

        int totalMstWeight = kruskalMST(V, edges);
        System.out.println("Total Minimum Spanning Tree Weight (Kruskal's): " + totalMstWeight);
        // Expected Output: 5
    }
}