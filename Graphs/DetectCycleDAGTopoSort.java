import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleDAGTopoSort {

    public static boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adjList) {
        int[] inDegree = new int[V];

        // Step 1: Compute incoming edges (in-degrees) for all vertices
        for (int i = 0; i < V; i++) {
            for (int neighbor : adjList.get(i)) {
                inDegree[neighbor]++;
            }
        }

        // Step 2: Enqueue all nodes with 0 in-degree (nodes with no dependencies)
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int count = 0; // Tracks the total number of processed nodes

        // Step 3: Standard BFS (Kahn's Algorithm)
        while (!q.isEmpty()) {
            int node = q.poll();
            count++; // Processed one valid node in the topological order

            // Decrease in-degrees of adjacent neighbors
            for (int neighbor : adjList.get(node)) {
                inDegree[neighbor]--;

                // If neighbor's dependencies drop to zero, push to queue
                if (inDegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        // CORE CHECK:
        // If count == V, all nodes were processed -> Valid DAG (No Cycle).
        // If count < V, some nodes were trapped in a cycle -> Cycle Exists!
        return count != V;
    }

    public static void main(String[] args) {
        int V = 4;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Seeding a cyclic graph: 0 -> 1 -> 2 -> 3 -> 1
        adjList.get(0).add(1);
        adjList.get(1).add(2);
        adjList.get(2).add(3);
        adjList.get(3).add(1); // Cycle loop back to node 1!

        boolean hasCycle = isCyclic(V, adjList);
        System.out.println("Does the directed graph contain a cycle? " + hasCycle);
        // Expected Output: true
    }
}