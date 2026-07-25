import java.util.Arrays;
import java.util.Vector;

public class AdjacencyMatrix {

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

        int[][] adjMat = new int[n+1][n+1];
        for (int i = 0; i < input.size(); i++) {
            Pair temp = input.get(i);
            adjMat[temp.source][temp.dest] = 1;
            // comment the below line for directed graph.
            adjMat[temp.dest][temp.source] = 1;
        }

        for (int i = 0; i < adjMat.length; i++) {
            System.out.println(Arrays.toString(adjMat[i]));
        }

    }
}
