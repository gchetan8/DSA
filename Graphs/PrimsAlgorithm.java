import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Vector;

// REUSED COMPONENT: Tuple for Min-Heap storing (weight, currentNode, parentNode)
class PrimTuple implements Comparable<PrimTuple> {
    int weight;
    int node;
    int parent;

    public PrimTuple(int weight, int node, int parent) {
        this.weight = weight;
        this.node = node;
        this.parent = parent;
    }

    @Override
    public int compareTo(PrimTuple other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class PrimsAlgorithm {

    public static int spanningTree(int V, ArrayList<ArrayList<NodeWeightPair>> adjList) {
        // Min-Heap tracking available frontier edges
        PriorityQueue<PrimTuple> pq = new PriorityQueue<>();

        // Visited array to ensure no cycles are formed
        int[] visited = new int[V];

        // Seed initial node 0 with weight 0 and parent -1
        pq.offer(new PrimTuple(0, 0, -1));

        int mstWeightSum = 0;
        // V - Vertices, E - Edges
        // O(VLogE)
        while (!pq.isEmpty()) {
            PrimTuple curr = pq.poll();
            int wt = curr.weight;
            int node = curr.node;

            // If the node is already included in the MST, skip it
            if (visited[node] == 1) continue;

            // Mark node as visited and add edge weight to total sum
            visited[node] = 1;
            mstWeightSum += wt;

            // VLogE
            // Loop through all outgoing edges of the newly added node
            for (NodeWeightPair neighbor : adjList.get(node)) {
                int adjNode = neighbor.node;
                int edgeWeight = neighbor.weight;

                // Push unvisited destination nodes onto the Priority Queue
                if (visited[adjNode] == 0) {
                    pq.offer(new PrimTuple(edgeWeight, adjNode, node));
                }
            }
        }

        return mstWeightSum;
    }

    public static void main(String[] args) {
        int V = 5;
        Vector<Pair> input = new Vector<>();

        // Seeding weighted undirected graph edges: Pair(u, v, weight)
        input.add(new Pair(0, 1, 2));
        input.add(new Pair(0, 2, 1));
        input.add(new Pair(1, 2, 1));
        input.add(new Pair(2, 3, 2));
        input.add(new Pair(3, 4, 1));
        input.add(new Pair(2, 4, 2));

        ArrayList<ArrayList<NodeWeightPair>> adjList = AdjacencyList.toWeightedAdjList(input, V);

        int totalWeight = spanningTree(V, adjList);
        System.out.println("Total Minimum Spanning Tree Weight: " + totalWeight);
        // Expected Output: 5 (Edges chosen: 0-2 (1), 2-1 (1), 2-3 (2), 3-4 (1))
    }
}