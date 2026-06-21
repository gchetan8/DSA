// Count occurrences of an element in a sorted array
// Uses first and last occurrence: count = last - first + 1
public class countOccurrences {

    static int firstOccurrence(int[] arr, int k) {
        int n = arr.length;
        int low = 0, high = n - 1, first = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == k) { first = mid; high = mid - 1; }
            else if (arr[mid] < k) low = mid + 1;
            else high = mid - 1;
        }
        return first;
    }

    static int lastOccurrence(int[] arr, int k) {
        int n = arr.length;
        int low = 0, high = n - 1, last = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == k) { last = mid; low = mid + 1; }
            else if (arr[mid] < k) low = mid + 1;
            else high = mid - 1;
        }
        return last;
    }

    static int count(int[] arr, int k) {
        int first = firstOccurrence(arr, k);
        if (first == -1) return 0;
        return lastOccurrence(arr, k) - first + 1;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 8, 8, 11, 13};
        System.out.println(count(arr, 8)); // 3
        System.out.println(count(arr, 5)); // 0
    }
}
