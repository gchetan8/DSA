import java.util.*;

public class PrintShortestPathDijkstra {

    static class DijkstraTuple implements Comparable<DijkstraTuple> {
        int distance;
        int node;
        public DijkstraTuple(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }
        @Override
        public int compareTo(DijkstraTuple other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static List<Integer> shortestPath(int n, ArrayList<ArrayList<NodeWeightPair>> adjList) {
        // Step 1: Initialize baseline Dijkstra constraints (1-indexed mapping bounds)
        int[] dist = new int[n + 1];
        int[] parent = new int[n + 1];
        Arrays.fill(dist, (int) 1e9);

        for (int i = 1; i <= n; i++) {
            parent[i] = i; // Initial breadcrumb points directly to self
        }

        // Target starting constraints: source is 1
        dist[1] = 0;
        PriorityQueue<DijkstraTuple> pq = new PriorityQueue<>();
        pq.offer(new DijkstraTuple(0, 1));

        // Step 2: Run core Dijkstra edge relaxation
        while (!pq.isEmpty()) {
            DijkstraTuple curr = pq.poll();
            int currentDistance = curr.distance;
            int node = curr.node;

            if (currentDistance > dist[node]) continue;

            ArrayList<NodeWeightPair> neighbors = adjList.get(node);
            for (int i = 0; i < neighbors.size(); i++) {
                NodeWeightPair neighborTuple = neighbors.get(i);
                int adjNode = neighborTuple.node;
                int edgeWeight = neighborTuple.weight;

                if (currentDistance + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = currentDistance + edgeWeight;
                    pq.offer(new DijkstraTuple(dist[adjNode], adjNode));

                    // CACHING THE PATH ORIGIN:
                    // Log that the absolute best path to adjNode came directly through node
                    parent[adjNode] = node;
                }
            }
        }

        // Step 3: Path Reconstruction & Validation
        // If the target destination node 'n' is untouched, no valid path loop connection exists
        if (dist[n] == (int) 1e9) {
            return List.of(-1);
        }

        List<Integer> path = new ArrayList<>();
        int currentNode = n;

        // Step backward tracking parents until hitting the source boundary
        while (parent[currentNode] != currentNode) {
            path.add(currentNode);
            currentNode = parent[currentNode];
        }
        path.add(currentNode); // Manually add the final source node element

        // Reverse the array list to structural sequence [source -> destination]
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        int n = 5; // 5 nodes total (1 to 5)
        Vector<Pair> input = new Vector<>();

        input.add(new Pair(1, 2, 2));
        input.add(new Pair(1, 4, 1));
        input.add(new Pair(2, 3, 4));
        input.add(new Pair(2, 5, 5));
        input.add(new Pair(3, 4, 3));
        input.add(new Pair(3, 5, 1));

        ArrayList<ArrayList<NodeWeightPair>> adjList = AdjacencyList.toWeightedAdjList(input, n);

        List<Integer> finalPath = shortestPath(n, adjList);
        System.out.println("Shortest path route sequence from 1 to " + n + ":");
        System.out.println(finalPath);
        // Expected Output: [1, 4, 3, 5]
    }
}