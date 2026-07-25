import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class TopologicalSortDFS {

    // Helper DFS function to explore deep branch paths before pushing to stack
    private static void dfs(int node, ArrayList<ArrayList<Integer>> adjList, int[] visited, Deque<Integer> stack) {
        visited[node] = 1;

        // Traverse all outgoing adjacent neighbors
        for (int neighbor : adjList.get(node)) {
            if (visited[neighbor] == 0) {
                dfs(neighbor, adjList, visited, stack);
            }
        }

        // BACKTRACKING STEP: Push current node to stack after all neighbor branches are finished
        stack.push(node);
    }

    public static int[] topoSort(int v, ArrayList<ArrayList<Integer>> adjList) {
        int[] visited = new int[v];
        Deque<Integer> stack = new ArrayDeque<>();

        // Loop through all vertices to handle disconnected DAG components
        for (int i = 0; i < v; i++) {
            if (visited[i] == 0) {
                dfs(i, adjList, visited, stack);
            }
        }

        // Pop items from the stack to build the topological sequence
        int[] result = new int[v];
        int idx = 0;
        while (!stack.isEmpty()) {
            result[idx++] = stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        int v = 6;
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        // Seeding DAG edges: 5->0, 5->2, 4->0, 4->1, 2->3, 3->1
        adjList.get(5).add(0);
        adjList.get(5).add(2);
        adjList.get(4).add(0);
        adjList.get(4).add(1);
        adjList.get(2).add(3);
        adjList.get(3).add(1);

        int[] order = topoSort(v, adjList);
        System.out.println("Topological Sort (DFS): " + java.util.Arrays.toString(order));
        // One valid output: [5, 4, 2, 3, 1, 0]
    }
}