import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class BFSGraph {

    // Helper method to perform BFS on a single component
    private static void bfsComponent(int start, ArrayList<ArrayList<Integer>> adjList, int[] visited, ArrayList<Integer> result) {
        visited[start] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int temp = q.poll();
            result.add(temp);

            ArrayList<Integer> tempList = adjList.get(temp);
            for (int i = 0; i < tempList.size(); i++) {
                int neighbor = tempList.get(i);
                if (visited[neighbor] != 1) {
                    q.offer(neighbor);
                    visited[neighbor] = 1;
                }
            }
        }
    }

    // Main structural driver to handle ALL connected components
    public static ArrayList<Integer> getAllComponentsBFS(ArrayList<ArrayList<Integer>> adjList, int v) {
        int[] visited = new int[v + 1]; // Supports 1-based indexing
        ArrayList<Integer> overallResult = new ArrayList<>();

        // Loop through all vertices from 1 to V to catch disconnected parts
        for (int i = 1; i <= v; i++) {
            if (visited[i] != 1) {
                // Launch BFS specifically for this newly discovered component
                bfsComponent(i, adjList, visited, overallResult);
            }
        }
        return overallResult;
    }

    public static void main(String[] args) {
        int v = 7; // 7 vertices total
        Vector<Pair> input = new Vector<>();

        // Component 1: Nodes 1, 2, 3
        input.add(new Pair(1, 2));
        input.add(new Pair(2, 3));

        // Component 2: Nodes 4, 5
        input.add(new Pair(4, 5));

        // Component 3: Nodes 6, 7
        input.add(new Pair(6, 7));

        ArrayList<ArrayList<Integer>> adjList = AdjacencyList.toAdjList(input, v);

        // Get full graph traversal across all distinct islands
        ArrayList<Integer> fullTraversal = getAllComponentsBFS(adjList, v);
        System.out.println("Complete Graph BFS Traversal: " + fullTraversal);
        // Expected Output: [1, 2, 3, 4, 5, 6, 7] (captures every isolated group!)
    }
}
