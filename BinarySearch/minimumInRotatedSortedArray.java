// LeetCode 153: Find Minimum in Rotated Sorted Array
public class minimumInRotatedSortedArray {

    // Brute Force: O(n)
    static int findMinBrute(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int x : arr) min = Math.min(min, x);
        return min;
    }

    // Optimal: Binary Search O(log n)
    // The sorted half always has its minimum at arr[low]; the pivot side has it at arr[mid]
    static int findMin(int[] arr) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int ans = Integer.MAX_VALUE;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[low] <= arr[mid]) {
                // left half sorted — minimum of this half is arr[low]
                ans = Math.min(ans, arr[low]);
                low = mid + 1;
            } else {
                // right half sorted — minimum of this half is arr[mid]
                ans = Math.min(ans, arr[mid]);
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 4, 5, 6, 6, 7, 7, 0, 0, 1, 2};
        System.out.println(findMinBrute(arr)); // 0
        System.out.println(findMin(arr));       // 0
    }
}
