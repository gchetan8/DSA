// LeetCode 33 - Search in Rotated Sorted Array
// Video: https://www.youtube.com/watch?v=r3pMQ8-Ad5s
public class searchInrotatedArray {

    // Brute Force: O(n)
    static int searchBrute(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == k) return i;
        }
        return -1;
    }

    // Optimal: Binary Search O(log n)
    static int search(int[] arr, int k) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == k) return mid;

            // left half is sorted
            if (arr[low] <= arr[mid]) {

                if (arr[low] <= k && k <= arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // right half is sorted
                if (arr[mid] <= k && k <= arr[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {7, 8, 9, 1, 2, 3, 4, 5, 6};

        System.out.println(searchBrute(arr, 1));  // 3
        System.out.println(search(arr, 1));        // 3
        System.out.println(search(arr, 10));       // -1
    }
}
