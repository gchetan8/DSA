import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

    // Simple coordinate container helper class
    static class Pair {
        int first, second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    private static void bfs(int startRow, int startCol, int[][] visited, char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        visited[startRow][startCol] = 1;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(startRow, startCol));

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            int row = curr.first;
            int col = curr.second;

            for (int delRow = -1; delRow <= 1; delRow++) {
                for (int delCol = -1; delCol <= 1; delCol++) {
                    int neighborRow = row + delRow;
                    int neighborCol = col + delCol;

                    // 1. Boundary Check: Ensure coordinates are valid inside the matrix
                    if (neighborRow >= 0 && neighborRow < n && neighborCol >= 0 && neighborCol < m) {
                        // 2. Connectivity & Visited Check: Is it unvisited land ('1')?
                        if (grid[neighborRow][neighborCol] == '1' && visited[neighborRow][neighborCol] != 1) {
                            visited[neighborRow][neighborCol] = 1;
                            q.offer(new Pair(neighborRow, neighborCol));
                        }
                    }
                }
            }
        }
    }

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int n = grid.length;
        int m = grid[0].length;
        int[][] visited = new int[n][m];
        int islandCount = 0;

        // Scan the entire 2D Grid row by row, column by column
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                // If we hit an unvisited piece of land, we discovered a new island!
                if (grid[row][col] == '1' && visited[row][col] != 1) {
                    islandCount++;
                    bfs(row, col, visited, grid); // Clear out the entire island
                }
            }
        }
        return islandCount;
    }

    public static void main(String[] args) {
        // Simulating a grid layout matching the video principles
        char[][] grid = {
                {'0', '1', '1', '0'},
                {'0', '1', '1', '0'},
                {'0', '0', '0', '1'},
                {'1', '1', '0', '1'}
        };

        int totalIslands = numIslands(grid);
        System.out.println("Total Number of Islands: " + totalIslands);
        // Expected Output: 2 
        // (Top-left cluster is connected; bottom-left and right sides merge diagonally!)
    }
}