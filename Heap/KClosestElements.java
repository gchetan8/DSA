import java.util.Arrays;
import java.util.PriorityQueue;

// Problem 4: K Closest Elements to the origin (smallest absolute values)
//
// Approach: Max-heap of size K based on absolute value.
// If current element is closer than the farthest in heap, replace it.
// Time: O(n log k)  |  Space: O(k)
public class KClosestElements {

    public static int[] kClosest(int[] arr, int k) {
        // Max-heap by absolute value — keeps the K smallest distances on top
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            (a, b) -> Math.abs(b) - Math.abs(a)
        );

        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) maxHeap.poll(); // remove farthest
        }

        int[] result = new int[k];
        int i = 0;
        while (!maxHeap.isEmpty()) result[i++] = maxHeap.poll();
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {10, -2, 3, 7, -5, 1};
        int k = 3;
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("K = " + k);
        System.out.println("K closest to origin: " + Arrays.toString(kClosest(arr, k)));
        // Sorted by abs: [1,-2,3,-5,7,10] → K=3 → [1, -2, 3]
    }
}
