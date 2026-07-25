import java.util.ArrayList;

public class CycleDetectionDFS {

    // Recursive helper method to track cycles deep within a component
    private static boolean checkForCycleDFS(int node, int parent,
                                            ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        visited[node] = true;

        // Traverse directly through the neighbors of the current node
        for (int neighbor : adjList.get(node)) {
            // Case 1: Neighbor hasn't been visited yet -> Deep dive recursively
            if (!visited[neighbor]) {
                // If any deeper recursive call spots a cycle, bubble the true up immediately
                if (checkForCycleDFS(neighbor, node, adjList, visited)) {
                    return true;
                }
            }
            /*
             * If the neighbor node is already visited AND it is not the node's parent,
             * it means we've circled back onto our own path. Cycle found!
             */
            else if (neighbor != parent) {
                return true;
            }
        }

        return false;
    }

    public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[V];

        // Loop ensures we scan all disconnected components of the graph
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                // Initial source calls launch with no real parent (represented by -1)
                if (checkForCycleDFS(i, -1, adjList, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int V = 4; // 4 vertices (0 to 3)
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Creating a cyclic graph: 0-1, 1-2, 2-3, 3-0
        adjList.get(0).add(1); adjList.get(1).add(0);
        adjList.get(1).add(2); adjList.get(2).add(1);
        adjList.get(2).add(3); adjList.get(3).add(2);
        adjList.get(3).add(0); adjList.get(0).add(3);

        boolean hasCycle = isCycle(V, adjList);
        System.out.println("Does the graph contain a cycle using DFS? " + hasCycle);
        // Expected Output: true
    }
}