import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Vector;

public class DijkstrasAlgorithmSet {

    // Simple tuple representation to track paths inside the Set structure
    static class SetTuple {
        int distance;
        int node;
        public SetTuple(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }
    }

    public static int[] dijkstra(ArrayList<ArrayList<NodeWeightPair>> adjList, int v, int src) {
        int[] dist = new int[v];
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0;

        // TreeSet initialization with custom strict tie-breaking comparator to avoid element duplication loss
        TreeSet<SetTuple> set = new TreeSet<>((a, b) -> {
            if (a.distance != b.distance) {
                return Integer.compare(a.distance, b.distance);
            }
            return Integer.compare(a.node, b.node); // Tie-breaker condition using node IDs
        });

        // Seed the initial structural source state into the Set
        set.add(new SetTuple(0, src));

        while (!set.isEmpty()) {
            // Extracts and deletes the absolute smallest element dynamically [1.2.3]
            SetTuple curr = set.pollFirst();
            int currentDistance = curr.distance;
            int node = curr.node;

            ArrayList<NodeWeightPair> neighbors = adjList.get(node);
            for (int i = 0; i < neighbors.size(); i++) {
                NodeWeightPair neighborTuple = neighbors.get(i);
                int adjNode = neighborTuple.node;
                int edgeWeight = neighborTuple.weight;

                // Path relaxation check
                if (currentDistance + edgeWeight < dist[adjNode]) {

                    // If the node was previously reachable, erase its old, stale path from the set first! [1.2.2]
                    if (dist[adjNode] != (int) 1e9) {
                        set.remove(new SetTuple(dist[adjNode], adjNode));
                    }

                    // Update the path value and insert the fresh tracking state
                    dist[adjNode] = currentDistance + edgeWeight;
                    set.add(new SetTuple(dist[adjNode], adjNode));
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int v = 6; // 6 nodes total (0 to 5)
        Vector<Pair> input = new Vector<>();

        input.add(new Pair(0, 1, 4));
        input.add(new Pair(0, 2, 4));
        input.add(new Pair(1, 2, 2));
        input.add(new Pair(2, 3, 3));
        input.add(new Pair(2, 4, 1));
        input.add(new Pair(2, 5, 6));
        input.add(new Pair(3, 5, 2));
        input.add(new Pair(4, 5, 3));

        ArrayList<ArrayList<NodeWeightPair>> adjList = AdjacencyList.toWeightedAdjList(input, v);

        int src = 0;
        int[] shortestDistances = dijkstra(adjList, v, src);

        System.out.println("Dijkstra Minimal Paths Matrix Output using Set:");
        System.out.println(Arrays.toString(shortestDistances));
        // Expected Output: [0, 4, 4, 7, 5, 7]
    }
}