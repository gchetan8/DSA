// LeetCode 69: Sqrt(x) — find the floor of the square root
public class sqrtBinarySearch {

    // Brute Force: O(sqrt(n))
    static long sqrtBrute(long n) {
        long ans = 1;
        for (long i = 1; i * i <= n; i++) ans = i;
        return ans;
    }

    // Optimal: Binary Search O(log n)
    static long sqrt(long n) {
        long low = 1, high = n;
        long ans = 1;
        while (low <= high) {
            long mid = (low + high) / 2;
            if (mid * mid <= n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        System.out.println(sqrtBrute(28)); // 5
        System.out.println(sqrt(28));       // 5
        System.out.println(sqrt(36));       // 6
    }
}
