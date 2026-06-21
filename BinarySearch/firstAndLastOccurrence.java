// LeetCode 34: Find First and Last Position of Element in Sorted Array
public class firstAndLastOccurrence {

    static int firstOccurrence(int[] arr, int k) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int first = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == k) {
                first = mid;
                high = mid - 1; // look left for earlier occurrence
            } else if (arr[mid] < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return first;
    }

    static int lastOccurrence(int[] arr, int k) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int last = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == k) {
                last = mid;
                low = mid + 1; // look right for later occurrence
            } else if (arr[mid] < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 8, 8, 11, 13};
        System.out.println(firstOccurrence(arr, 8)); // 3
        System.out.println(lastOccurrence(arr, 8));  // 5
        System.out.println(firstOccurrence(arr, 5)); // -1
    }
}
