import java.util.ArrayList;
import java.util.Vector;

public class NumberOfProvinces {

    private static void dfs(int node, ArrayList<ArrayList<Integer>> adjList, int[] visited) {
        visited[node] = 1;

        // Loop directly through the neighbors of the current node
        ArrayList<Integer> neighbors = adjList.get(node);
        for (int i = 0; i < neighbors.size(); i++) {
            int neighbor = neighbors.get(i);
            if (visited[neighbor] != 1) {
                dfs(neighbor, adjList, visited);
            }
        }
    }

    public static int numProvinces(ArrayList<ArrayList<Integer>> adjList, int v) {
        // Size v + 1 to easily map 1-indexed nodes safely
        int[] visited = new int[v + 1];
        int count = 0;

        for (int i = 1; i <= v; i++) {
            if (visited[i] != 1) {
                count++; // Found a brand new unvisited province!
                dfs(i, adjList, visited); // Clear out all nodes in this province
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int v = 8; // 8 vertices total

        Vector<Pair> input = new Vector<>();
        // Province 1 edges
        input.add(new Pair(1, 2));
        input.add(new Pair(2, 3));
        input.add(new Pair(1, 3));

        // Province 2 edges
        input.add(new Pair(4, 5));
        input.add(new Pair(5, 6));
        input.add(new Pair(4, 6));

        // Province 3 edges
        input.add(new Pair(7, 8));

        // Generate the type-safe adjacency list structure we built earlier
        ArrayList<ArrayList<Integer>> adjList = AdjacencyList.toAdjList(input, v);

        int result = numProvinces(adjList, v);
        System.out.println("Total Number of Provinces: " + result);
    }
}