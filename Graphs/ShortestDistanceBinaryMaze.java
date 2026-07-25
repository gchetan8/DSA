import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceBinaryMaze {

    // Helper tuple class to store grid cell coordinates and accumulated distance
    static class MatrixTuple {
        int dist;
        int row;
        int col;

        public MatrixTuple(int dist, int row, int col) {
            this.dist = dist;
            this.row = row;
            this.col = col;
        }
    }

    public static int shortestPath(int[][] grid, int[] source, int[] destination) {
        // Base Case 1: Source is already the destination
        if (source[0] == destination[0] && source[1] == destination[1]) {
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;

        // Base Case 2: Source or destination cell is blocked by a wall (0)
        if (grid[source[0]][source[1]] == 0 || grid[destination[0]][destination[1]] == 0) {
            return -1;
        }

        // Initialize 2D Distance Tracking Matrix with infinity values
        int[][] distMatrix = new int[n][m];
        for (int[] row : distMatrix) {
            Arrays.fill(row, (int) 1e9);
        }

        // Seed source cell state
        distMatrix[source[0]][source[1]] = 0;
        Queue<MatrixTuple> q = new LinkedList<>();
        q.offer(new MatrixTuple(0, source[0], source[1]));

        // Direction arrays for 4-directional movement (Up, Right, Down, Left)
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        // Level-order BFS traversal
        while (!q.isEmpty()) {
            MatrixTuple curr = q.poll();
            int d = curr.dist;
            int r = curr.row;
            int c = curr.col;

            for (int i = 0; i < 4; i++) {
                int newRow = r + delRow[i];
                int newCol = c + delCol[i];

                // Boundary verification + unblocked path check (grid[newRow][newCol] == 1)
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m
                        && grid[newRow][newCol] == 1) {

                    // Path Relaxation Check
                    if (d + 1 < distMatrix[newRow][newCol]) {
                        distMatrix[newRow][newCol] = d + 1;

                        // Early Termination Optimization: Stop immediately upon reaching destination
                        if (newRow == destination[0] && newCol == destination[1]) {
                            return d + 1;
                        }

                        q.offer(new MatrixTuple(d + 1, newRow, newCol));
                    }
                }
            }
        }

        // Destination is unreachable
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 1}
        };
        int[] source = {0, 1};
        int[] destination = {2, 2};

        int shortestSteps = shortestPath(grid, source, destination);
        System.out.println("Shortest Distance in Binary Maze: " + shortestSteps);
        // Expected Output: 3
    }
}