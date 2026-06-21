// LeetCode 1482: Minimum Number of Days to Make m Bouquets
// Need m bouquets, each needs k consecutive bloomed flowers
public class minimumDaysBouquets {

    static boolean isPossible(int[] bloomDay, int day, int m, int k) {
        int bouquets = 0, consecutive = 0;
        for (int d : bloomDay) {
            if (d <= day) {
                consecutive++;
                if (consecutive == k) { bouquets++; consecutive = 0; }
            } else {
                consecutive = 0;
            }
        }
        return bouquets >= m;
    }

    // Brute Force: try each day from 1 to max O(max * n)
    static int minDaysBrute(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length) return -1;
        int max = 0;
        for (int d : bloomDay) max = Math.max(max, d);
        for (int day = 1; day <= max; day++) {
            if (isPossible(bloomDay, day, m, k)) return day;
        }
        return -1;
    }

    // Optimal: Binary Search O(n * log(max))
    static int minDays(int[] bloomDay, int m, int k) {
        if ((long) m * k > bloomDay.length) return -1;
        int low = 1, high = 0;
        for (int d : bloomDay) high = Math.max(high, d);
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible(bloomDay, mid, m, k)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] bloomDay = {7, 7, 7, 7, 13, 11, 12, 7};
        System.out.println(minDaysBrute(bloomDay, 2, 3)); // 12
        System.out.println(minDays(bloomDay, 2, 3));       // 12
    }
}
