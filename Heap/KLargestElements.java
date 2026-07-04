import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// Problem 3: K Largest Elements in an array
//
// Approach: Min-heap of size K.
// For each element, if it's larger than the heap's min, replace it.
// The heap contains the K largest at the end.
// Time: O(n log k)  |  Space: O(k)
public class KLargestElements {

    public static List<Integer> kLargest(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : arr) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll(); // remove the smallest
        }

        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 5, 6, 4};
        int k = 3;
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("K = " + k);
        System.out.println("K largest: " + kLargest(arr, k));
        // Expected: [4, 5, 6] (in some order)
    }
}
