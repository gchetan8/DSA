import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectionBFS {

    // Simple Node-Parent structure to track entry origin inside the queue
    static class NodeParentPair {
        int node;
        int parent;
        public NodeParentPair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }

    // Helper method to execute BFS on a single component
    private static boolean checkForCycle(int src, ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        visited[src] = true;

        Queue<NodeParentPair> q = new LinkedList<>();
        // Source node starts with no parent (indicated by -1)
        q.offer(new NodeParentPair(src, -1));

        while (!q.isEmpty()) {
            NodeParentPair curr = q.poll();
            int node = curr.node;
            int parent = curr.parent;

            // Iterate directly through neighbors of the current node
            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.offer(new NodeParentPair(neighbor, node));
                }
                /*
                 * If the neighbor is visited AND it is not the node we just came from,
                 * we have detected a cycle collision!
                 */
                else if (neighbor != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isCycle(int V, ArrayList<ArrayList<Integer>> adjList) {
        boolean[] visited = new boolean[V];

        // Loop guarantees we check all disconnected components of the graph
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (checkForCycle(i, adjList, visited)) {
                    return true; // Cycle found in this component
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
        System.out.println("Does the graph contain a cycle? " + hasCycle);
        // Expected Output: true
    }
}