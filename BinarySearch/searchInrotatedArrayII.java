// LeetCode 81: Search in Rotated Sorted Array II (array may have duplicates)
// Returns true/false unlike LeetCode 33 which returns index
public class searchInrotatedArrayII {

    static boolean search(int[] arr, int k) {
        int n = arr.length;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == k) return true;

            // duplicates at boundaries — shrink and retry
            if (arr[low] == arr[mid] && arr[mid] == arr[high]) {
                low++;
                high--;
                continue;
            }

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
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 3, 3, 3, 3};
        System.out.println(search(arr, 1)); // true
        System.out.println(search(arr, 5)); // false
    }
}
