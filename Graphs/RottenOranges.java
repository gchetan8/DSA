import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    // Helper tuple class to track row, column, and current time offset
    static class OrangeTuple {
        int row, col, time;
        public OrangeTuple(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int n = grid.length;
        int m = grid[0].length;

        Queue<OrangeTuple> q = new LinkedList<>();
        int[][] visited = new int[n][m];
        int cntFresh = 0;

        // Phase 1: Initialize Multi-Source positions & count initial fresh oranges
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    // Seed all starting rotten nodes into the queue simultaneously at time = 0
                    q.offer(new OrangeTuple(i, j, 0));
                    visited[i][j] = 2; // Mark as processed/rotten
                } else {
                    visited[i][j] = 0;
                }

                if (grid[i][j] == 1) {
                    cntFresh++;
                }
            }
        }

        int maxTime = 0;
        int cntRottenDuringBfs = 0;

        // 4-Directional movement arrays (Up, Right, Down, Left)
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        // Phase 2: Execute Level-Order concurrent rot simulation
        while (!q.isEmpty()) {
            OrangeTuple curr = q.poll();
            int r = curr.row;
            int c = curr.col;
            int t = curr.time;

            maxTime = Math.max(maxTime, t);

            for (int i = 0; i < 4; i++) {
                int neighborRow = r + delRow[i];
                int neighborCol = c + delCol[i];

                // Boundary verification
                if (neighborRow >= 0 && neighborRow < n && neighborCol >= 0 && neighborCol < m) {
                    // Check if neighbor is a fresh orange and hasn't been touched yet
                    if (grid[neighborRow][neighborCol] == 1 && visited[neighborRow][neighborCol] != 2) {
                        visited[neighborRow][neighborCol] = 2; // Rot the orange
                        cntRottenDuringBfs++;
                        q.offer(new OrangeTuple(neighborRow, neighborCol, t + 1));
                    }
                }
            }
        }

        // Phase 3: Final validation check if any fresh oranges survived unreachable
        if (cntRottenDuringBfs != cntFresh) {
            return -1;
        }

        return maxTime;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        int timeElapsed = orangesRotting(grid);
        System.out.println("Minimum minutes to rot all oranges: " + timeElapsed);
        // Expected Output: 4
    }
}