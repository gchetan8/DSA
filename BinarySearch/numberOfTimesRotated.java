// Find how many times a sorted array has been rotated
// Answer = index of the minimum element
public class numberOfTimesRotated {

    static int findKRotation(int[] arr) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int ans = Integer.MAX_VALUE;
        int index = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[low] <= arr[mid]) {
                // left half sorted — track its minimum
                if (arr[low] < ans) {
                    ans = arr[low];
                    index = low;
                }
                low = mid + 1;
            } else {
                // right half sorted — track its minimum
                if (arr[mid] < ans) {
                    ans = arr[mid];
                    index = mid;
                }
                high = mid - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        int[] arr = {4, 4, 5, 6, 6, 7, 7, 0, 0, 1, 2};
        System.out.println(findKRotation(arr)); // 4  (0 is at index 4, so rotated 4 times)
    }
}
