// LeetCode 875: Koko Eating Bananas
// Find minimum eating speed k so all piles are eaten within h hours
public class kokoEatingBananas {

    static int maxPile(int[] piles) {
        int max = 0;
        for (int p : piles) max = Math.max(max, p);
        return max;
    }

    static long hoursTaken(int[] piles, int k) {
        long hours = 0;
        for (int p : piles) hours += Math.ceil((double) p / k);
        return hours;
    }

    // Brute Force: try every speed from 1 to max O(max * n)
    static int minEatingSpeedBrute(int[] piles, int h) {
        for (int k = 1; k <= maxPile(piles); k++) {
            if (hoursTaken(piles, k) <= h) return k;
        }
        return -1;
    }

    // Optimal: Binary Search O(n * log(max))
    static int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = maxPile(piles);
        while (low <= high) {
            int mid = (low + high) / 2;
            if (hoursTaken(piles, mid) <= h) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        System.out.println(minEatingSpeedBrute(piles, h)); // 4
        System.out.println(minEatingSpeed(piles, h));       // 4
    }
}
