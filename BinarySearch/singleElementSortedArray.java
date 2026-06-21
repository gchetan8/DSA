// LeetCode 540: Single Element in a Sorted Array
// Every element appears twice except one — find it in O(log n)
public class singleElementSortedArray {

    // Brute Force: XOR all elements O(n)
    static int singleNonDuplicateBrute(int[] arr) {
        int ans = 0;
        for (int x : arr) ans ^= x;
        return ans;
    }

    // Optimal: Binary Search O(log n)
    // Before the single element: pairs start at even indices (arr[even] == arr[even+1])
    // After  the single element: pairs start at odd  indices (arr[odd]  == arr[odd-1])
    static int singleNonDuplicate(int[] arr) {
        int n = arr.length;
        if (n == 1) return arr[0];
        if (arr[0] != arr[1]) return arr[0];
        if (arr[n - 1] != arr[n - 2]) return arr[n - 1];

        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1]) {
                return arr[mid];
            }
            // still on the left (correct) side — move right
            if ((mid % 2 == 1 && arr[mid] == arr[mid - 1]) ||
                (mid % 2 == 0 && arr[mid] == arr[mid + 1])) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println(singleNonDuplicateBrute(arr)); // 2
        System.out.println(singleNonDuplicate(arr));       // 2
    }
}
