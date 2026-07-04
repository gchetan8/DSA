import java.util.Arrays;
import java.util.PriorityQueue;

// Problem 1: Sort a K-sorted (nearly sorted) array
// Each element is at most K positions away from its sorted position.
//
// Approach: Min-heap of size (K+1).
// Slide a window of K+1 elements; the heap always gives the current minimum.
// Time: O(n log k)  |  Space: O(k)
public class SortKSortedArray {

    public static int[] sortKSorted(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[n];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Fill heap with first k+1 elements
        for (int i = 0; i <= Math.min(k, n - 1); i++) {
            minHeap.offer(arr[i]);
        }

        int idx = 0;
        for (int i = k + 1; i < n; i++) {
            result[idx++] = minHeap.poll();
            minHeap.offer(arr[i]);
        }

        // Drain remaining elements
        while (!minHeap.isEmpty()) result[idx++] = minHeap.poll();

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {6, 5, 3, 2, 8, 10, 9};
        int k = 3;
        System.out.println("Input:  " + Arrays.toString(arr));
        System.out.println("K = " + k);
        System.out.println("Output: " + Arrays.toString(sortKSorted(arr, k)));
        // Expected: [2, 3, 5, 6, 8, 9, 10]
    }
}
