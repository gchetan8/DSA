import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Vector;

public class DijkstrasAlgorithmPQ {

    // Helper tuple configuration that lets the PriorityQueue sort elements by absolute distance
    static class DijkstraTuple implements Comparable<DijkstraTuple> {
        int distance;
        int node;

        public DijkstraTuple(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }

        // Min-Heap sort rule: Process paths with the smallest accumulated distance values first
        @Override
        public int compareTo(DijkstraTuple other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    // Main Dijkstra algorithm function
    public static int[] dijkstra(ArrayList<ArrayList<NodeWeightPair>> adjList, int v, int src) {
        // Setup distance tracking array initialized to infinity
        int[] dist = new int[v];
        Arrays.fill(dist, (int) 1e9);

        // Base case setup: Distance from source to source is always 0
        dist[src] = 0;

        // Instantiate min-heap Priority Queue
        PriorityQueue<DijkstraTuple> pq = new PriorityQueue<>();

        // Seed the starting node into the Priority Queue at distance = 0
        pq.offer(new DijkstraTuple(0, src));

        while (!pq.isEmpty()) {
            DijkstraTuple curr = pq.poll();
            int currentDistance = curr.distance;
            int node = curr.node;

            // Optimization: Skip stale processing if a shorter path to this node was already completed
            if (currentDistance > dist[node]) continue;

            // Loop directly through neighbors stored inside your upgraded list architecture
            ArrayList<NodeWeightPair> neighbors = adjList.get(node);
            for (int i = 0; i < neighbors.size(); i++) {
                NodeWeightPair neighborTuple = neighbors.get(i);
                int adjNode = neighborTuple.node;
                int edgeWeight = neighborTuple.weight;

                // Path relaxation logic: Is the new path option shorter than the previous best?
                if (currentDistance + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = currentDistance + edgeWeight;
                    // Push updated minimal path footprint back onto the min-heap
                    pq.offer(new DijkstraTuple(dist[adjNode], adjNode));
                }
            }
        }
        return dist;
    }

    // Main function to run the execution path
    public static void main(String[] args) {
        int v = 6; // 6 vertices total (nodes 0 to 5)
        Vector<Pair> input = new Vector<>();

        input.add(new Pair(0, 1, 4));
        input.add(new Pair(0, 2, 4));
        input.add(new Pair(1, 2, 2));
        input.add(new Pair(2, 3, 3));
        input.add(new Pair(2, 4, 1));
        input.add(new Pair(2, 5, 6));
        input.add(new Pair(3, 5, 2));
        input.add(new Pair(4, 5, 3));

        // 1. Convert input to our weighted adjacency list architecture
        ArrayList<ArrayList<NodeWeightPair>> adjList = AdjacencyList.toWeightedAdjList(input, v);

        // 2. Compute shortest path metrics starting from Source 0
        int src = 0;
        int[] shortestDistances = dijkstra(adjList, v, src);

        // 3. Print out the final relaxed paths matrix array
        System.out.println("Shortest distances from source node " + src + ":");
        System.out.println(Arrays.toString(shortestDistances));
        // Expected Output: [0, 4, 4, 7, 5, 7]
    }
}