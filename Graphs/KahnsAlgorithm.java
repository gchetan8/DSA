import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class KahnsAlgorithm {

    public static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adjList) {
        int[] inDegree = new int[V];

        // Step 1: Calculate the in-degree for every vertex
        for (int i = 0; i < V; i++) {
            for (int neighbor : adjList.get(i)) {
                inDegree[neighbor]++;
            }
        }

        // Step 2: Push all nodes with in-degree 0 into the Queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        int[] topoOrder = new int[V];
        int index = 0;

        // Step 3: Level-order BFS traversal
        while (!q.isEmpty()) {
            int node = q.poll();
            topoOrder[index++] = node;

            // Reduce in-degree for all adjacent neighbors
            for (int neighbor : adjList.get(node)) {
                inDegree[neighbor]--;

                // If neighbor's in-degree hits 0, all dependencies are resolved
                if (inDegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        // CYCLE CHECK: If processed count != total vertices, the graph has a cycle!
        if (index != V) {
            System.out.println("Graph contains a cycle! Topological sort impossible.");
            return new int[0];
        }

        return topoOrder;
    }

    public static void main(String[] args) {
        int V = 6;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Seeding DAG edges: 5->0, 5->2, 4->0, 4->1, 2->3, 3->1
        adjList.get(5).add(0);
        adjList.get(5).add(2);
        adjList.get(4).add(0);
        adjList.get(4).add(1);
        adjList.get(2).add(3);
        adjList.get(3).add(1);

        int[] result = topoSort(V, adjList);
        System.out.println("Topological Sort (Kahn's Algo / BFS): " + java.util.Arrays.toString(result));
        // Valid Output: [4, 5, 0, 2, 3, 1]
    }
}