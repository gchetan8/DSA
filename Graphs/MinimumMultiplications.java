import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MinimumMultiplications {

    // Helper class to store current number along with accumulated multiplication steps
    static class StepPair {
        int node;
        int steps;

        public StepPair(int node, int steps) {
            this.node = node;
            this.steps = steps;
        }
    }

    public static int minimumMultiplications(int[] arr, int start, int end) {
        // Base case shortcut
        if (start == end) return 0;

        int MOD = 100000;

        // Distance array initialized to infinity to track minimal steps to each number
        int[] dist = new int[MOD];
        Arrays.fill(dist, (int) 1e9);

        // Seed initial state
        dist[start] = 0;
        Queue<StepPair> q = new LinkedList<>();
        q.offer(new StepPair(start, 0));

        // Standard BFS
        while (!q.isEmpty()) {
            StepPair curr = q.poll();
            int node = curr.node;
            int steps = curr.steps;

            for (int factor : arr) {
                // Calculate new state using modulo arithmetic
                int nextNum = (int) (((long) node * factor) % MOD);

                // Path relaxation check
                if (steps + 1 < dist[nextNum]) {
                    dist[nextNum] = steps + 1;

                    // Early termination check upon finding the end target
                    if (nextNum == end) {
                        return steps + 1;
                    }

                    q.offer(new StepPair(nextNum, steps + 1));
                }
            }
        }

        // Return -1 if target number is unreachable
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 7};
        int start = 3;
        int end = 30;

        int steps = minimumMultiplications(arr, start, end);
        System.out.println("Minimum multiplications needed: " + steps);
        // Expected Output: 2 (3 * 2 = 6, 6 * 5 = 30)
    }
}