import java.util.Arrays;
import java.util.Vector;

public class BellmanFordAlgorithm {

    public static int[] bellmanFord(int V, Vector<Pair> edges, int src) {
        // Step 1: Set up distance array initialized to a large value (1e8 used to prevent overflow additions)
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e8);
        dist[src] = 0;

        // Step 2: Relax all graph edges exactly V - 1 times sequentially
        for (int i = 1; i <= V - 1; i++) {
            for (int j = 0; j < edges.size(); j++) {
                Pair edge = edges.get(j);
                int u = edge.source;
                int v = edge.dest;
                int wt = edge.weight;

                // Edge relaxation check condition
                if (dist[u] != (int) 1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Step 3: Run the V-th bonus iteration loop to detect negative cycle presence
        for (int j = 0; j < edges.size(); j++) {
            Pair edge = edges.get(j);
            int u = edge.source;
            int v = edge.dest;
            int wt = edge.weight;

            // If a value shrinks further during this pass, a negative weight cycle is active!
            if (dist[u] != (int) 1e8 && dist[u] + wt < dist[v]) {
                // Return an array consisting of only -1
                return new int[]{-1};
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int V = 6; // 6 nodes (0 to 5)
        Vector<Pair> edges = new Vector<>();

        edges.add(new Pair(3, 2, 6));
        edges.add(new Pair(5, 3, 1));
        edges.add(new Pair(0, 1, 5));
        edges.add(new Pair(1, 5, -3));
        edges.add(new Pair(1, 2, -2));
        edges.add(new Pair(2, 4, 3));
        edges.add(new Pair(3, 4, -2));
        edges.add(new Pair(4, 5, 3));

        int src = 0;
        int[] shortestPaths = bellmanFord(V, edges, src);

        System.out.println("Bellman-Ford Single Source Shortest Paths from Node " + src + ":");
        System.out.println(Arrays.toString(shortestPaths));
        // Expected Output matching values: [0, 5, 3, 3, 1, 2]
    }
}