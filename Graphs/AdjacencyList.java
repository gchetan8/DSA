import java.util.ArrayList;
import java.util.Vector;

public class AdjacencyList {

    // For Unweighted Graphs (Returns list of integers)
    public static ArrayList<ArrayList<Integer>> toAdjList(Vector<Pair> input, int n) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < input.size(); i++) {
            Pair temp = input.get(i);
            adjList.get(temp.source).add(temp.dest);
            adjList.get(temp.dest).add(temp.source);
        }
        return adjList;
    }

    // New Overloaded Method: For Weighted Graphs (Returns list of NodeWeightPairs)
    public static ArrayList<ArrayList<NodeWeightPair>> toWeightedAdjList(Vector<Pair> input, int n) {
        ArrayList<ArrayList<NodeWeightPair>> adjList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < input.size(); i++) {
            Pair temp = input.get(i);
            // Storing both destination node and edge weight dynamically
            adjList.get(temp.source).add(new NodeWeightPair(temp.dest, temp.weight));
            adjList.get(temp.dest).add(new NodeWeightPair(temp.source, temp.weight));
        }
        return adjList;
    }

    public static void main(String[] args) {

        /*

        1 - 2
        |   | \
        |   |  5
        |   | /
        3 - 4
        */

        int n = 5;
        int m = 6;
        Vector<Pair> input = new Vector<>();
        input.add(new Pair(1, 2));
        input.add(new Pair(1, 3));
        input.add(new Pair(3, 4));
        input.add(new Pair(2, 4));
        input.add(new Pair(2, 5));
        input.add(new Pair(4, 5));

        ArrayList<ArrayList<Integer>> adjList = toAdjList(input, n);
        for (int i = 0; i < adjList.size(); i++) {
            System.out.println(adjList.get(i));
        }

    }
}
