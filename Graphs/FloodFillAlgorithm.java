import java.util.Arrays;

public class FloodFillAlgorithm {

    private static void dfs(int row, int col, int[][] ans, int[][] image,
                            int initialColor, int newColor, int[] delRow, int[] delCol) {
        // Mark current pixel with the new color on the output canvas
        ans[row][col] = newColor;

        int n = image.length;
        int m = image[0].length;

        // Cleanly inspect all 4 adjacent directions (Up, Right, Down, Left)
        for (int i = 0; i < 4; i++) {
            int neighborRow = row + delRow[i];
            int neighborCol = col + delCol[i];

            // 1. Boundary Check: Ensure neighbors remain inside the pixel bounds
            if (neighborRow >= 0 && neighborRow < n && neighborCol >= 0 && neighborCol < m) {
                /*
                 * 2. Validity & Visit Check:
                 *    - Does the neighbor match the original pixel color?
                 *    - Has it already been changed to the new color? (Acts as our visited check)
                 */
                if (image[neighborRow][neighborCol] == initialColor && ans[neighborRow][neighborCol] != newColor) {
                    dfs(neighborRow, neighborCol, ans, image, initialColor, newColor, delRow, delCol);
                }
            }
        }
    }

    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int initialColor = image[sr][sc];

        // Safety Optimization: If target color matches initial color, return immediately
        if (initialColor == newColor) return image;

        int n = image.length;
        int m = image[0].length;

        // Clone the initial image grid to avoid dirtying the source input data
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            ans[i] = image[i].clone();
        }

        // Coordinate offsets to map Up, Right, Down, Left paths sequentially
        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        // Fire off the recursive DFS flood filling sequence
        dfs(sr, sc, ans, image, initialColor, newColor, delRow, delCol);

        return ans;
    }

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        int sr = 1, sc = 1, newColor = 2;

        int[][] result = floodFill(image, sr, sc, newColor);

        System.out.println("Flood Filled Image Output:");
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
        /*
         * Expected Output (Target matching connected '1' paths switched to '2'):
         * [2, 2, 2]
         * [2, 2, 0]
         * [2, 0, 1]
         */
    }
}