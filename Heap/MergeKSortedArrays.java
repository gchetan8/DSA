import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// Problem 5: Merge K sorted arrays into one sorted array
//
// Approach: Min-heap stores (value, arrayIndex, elementIndex).
// Always extract the minimum across all current front elements.
// Time: O(n*k log k) where n = avg length  |  Space: O(k)
public class MergeKSortedArrays {

    static class Node {
        int val, arrIdx, elemIdx;
        Node(int val, int arrIdx, int elemIdx) {
            this.val = val; this.arrIdx = arrIdx; this.elemIdx = elemIdx;
        }
    }

    public static List<Integer> mergeK(int[][] arrays) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Seed heap with the first element of each array
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Node(arrays[i][0], i, 0));
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Node curr = minHeap.poll();
            result.add(curr.val);

            int nextElem = curr.elemIdx + 1;
            if (nextElem < arrays[curr.arrIdx].length) {
                minHeap.offer(new Node(arrays[curr.arrIdx][nextElem], curr.arrIdx, nextElem));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] arrays = {
            {1, 4, 7},
            {2, 5, 8},
            {3, 6, 9}
        };
        for (int[] a : arrays) System.out.println("Array: " + Arrays.toString(a));
        System.out.println("Merged: " + mergeK(arrays));
        // Expected: [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
