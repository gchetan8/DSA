import java.util.*;

public class ShortestPathUnitWeights {

    public static int[] shortestPath(ArrayList<ArrayList<Integer>> adjList, int n, int src) {
        // Setup distance tracking array initialized to infinity (1e9 representation)
        int[] dist = new int[n];
        Arrays.fill(dist, (int) 1e9);

        // Base Case: distance from source to source is always 0
        dist[src] = 0;

        // BFS level-order tracking queue
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);

        while (!q.isEmpty()) {
            int node = q.poll();

            // Direct iteration over neighbors within the recycled ArrayList<ArrayList<Integer>>
            ArrayList<Integer> neighbors = adjList.get(node);
            for (int i = 0; i < neighbors.size(); i++) {
                int neighbor = neighbors.get(i);

                // Distance relaxation step
                if (dist[node] + 1 < dist[neighbor]) {
                    dist[neighbor] = dist[node] + 1;
                    q.offer(neighbor);
                }
            }
        }

        // Build the final target response array transforming unreached nodes into -1
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (dist[i] == (int) 1e9) {
                ans[i] = -1;
            } else {
                ans[i] = dist[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = 9; // 9 vertices total (0 to 8)

        Vector<Pair> input = new Vector<>();
        input.add(new Pair(0, 1));
        input.add(new Pair(0, 3));
        input.add(new Pair(1, 2));
        input.add(new Pair(1, 3));
        input.add(new Pair(2, 6));
        input.add(new Pair(3, 4));
        input.add(new Pair(4, 5));
        input.add(new Pair(5, 6));
        input.add(new Pair(6, 7));
        input.add(new Pair(6, 8));
        input.add(new Pair(7, 8));

        int src = 0;

        // Generate adjacency list using our reusable converter class
        ArrayList<ArrayList<Integer>> adjList = AdjacencyList.toAdjList(input, n);

        // Calculate shortest path metrics
        int[] result = shortestPath(adjList, n, src);

        System.out.println("Shortest unit distances from source " + src + ":");
        System.out.println(Arrays.toString(result));
        // Expected Output: [0, 1, 2, 1, 2, 3, 3, 4, 4]
    }
}