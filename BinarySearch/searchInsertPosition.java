// LeetCode 35: Search Insert Position
// Same logic as lower_bound — first index where arr[i] >= target
public class searchInsertPosition {

    static int searchInsert(int[] arr, int target) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int ans = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        System.out.println(searchInsert(arr, 5)); // 2
        System.out.println(searchInsert(arr, 2)); // 1
        System.out.println(searchInsert(arr, 7)); // 4
        System.out.println(searchInsert(arr, 0)); // 0
    }
}
