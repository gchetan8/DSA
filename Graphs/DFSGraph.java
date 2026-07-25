import java.util.ArrayList;
import java.util.Vector;

public class DFSGraph {

    public static ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adjList, int start, int[] visited, ArrayList<Integer> result) {
        visited[start] = 1;
        result.add(start);
        for (Integer it : adjList.get(start)) {
            if(visited[it] != 1) {
                dfs(adjList, it, visited,result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 5;
        int m = 6;
        Vector<Pair> input = new Vector<>();
        input.add(new Pair(1, 2));
        input.add(new Pair(1, 3));
        input.add(new Pair(3, 4));
        input.add(new Pair(2, 4));
        input.add(new Pair(2, 5));
        input.add(new Pair(4, 5));

        ArrayList<ArrayList<Integer>> adjList = AdjacencyList.toAdjList(input, n);
        int[] visited = new int[n + 1];
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> dfsTraversal = dfs(adjList, 1, visited, result);
        System.out.println(dfsTraversal);
    }
}
