// LeetCode 1011: Capacity to Ship Packages Within D Days
// Find minimum ship capacity so all weights are shipped in exactly D days
public class capacityShipPackages {

    static int daysNeeded(int[] weights, int capacity) {
        int days = 1, load = 0;
        for (int w : weights) {
            if (load + w > capacity) { days++; load = w; }
            else load += w;
        }
        return days;
    }

    // Brute Force: try every capacity from max to sum O((sum - max) * n)
    static int shipWithinDaysBrute(int[] weights, int days) {
        int max = 0, total = 0;
        for (int w : weights) { max = Math.max(max, w); total += w; }
        for (int cap = max; cap <= total; cap++) {
            if (daysNeeded(weights, cap) <= days) return cap;
        }
        return -1;
    }

    // Optimal: Binary Search O(n * log(sum - max))
    static int shipWithinDays(int[] weights, int days) {
        int low = 0, high = 0;
        for (int w : weights) { low = Math.max(low, w); high += w; }
        while (low <= high) {
            int mid = (low + high) / 2;
            if (daysNeeded(weights, mid) <= days) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(shipWithinDaysBrute(weights, 5)); // 15
        System.out.println(shipWithinDays(weights, 5));       // 15
    }
}
