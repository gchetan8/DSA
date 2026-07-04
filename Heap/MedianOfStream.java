import java.util.Collections;
import java.util.PriorityQueue;

// Problem 6: Median of a Stream
// Find the median after each insertion from a data stream.
//
// Approach: Two heaps
//   maxHeap → lower half  (max at top = left median candidate)
//   minHeap → upper half  (min at top = right median candidate)
//
// Invariant: maxHeap.size() == minHeap.size()   OR
//            maxHeap.size() == minHeap.size() + 1
//
// Median:
//   odd total  → maxHeap.peek()
//   even total → (maxHeap.peek() + minHeap.peek()) / 2.0
//
// Time: O(log n) per insertion, O(1) for median  |  Space: O(n)
public class MedianOfStream {

    PriorityQueue<Integer> maxHeap; // lower half
    PriorityQueue<Integer> minHeap; // upper half

    public MedianOfStream() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // Step 1: push to lower half
        maxHeap.offer(num);

        // Step 2: balance — lower max must be ≤ upper min
        if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
            minHeap.offer(maxHeap.poll());
        }

        // Step 3: keep size difference ≤ 1 (maxHeap can have one extra)
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double getMedian() {
        if (maxHeap.isEmpty()) throw new RuntimeException("No elements");
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianOfStream stream = new MedianOfStream();
        int[] nums = {5, 15, 1, 3, 2, 8, 7, 9, 10, 6, 11, 4};

        System.out.println("Stream | Median");
        System.out.println("-------+-------");
        for (int num : nums) {
            stream.addNum(num);
            System.out.printf("add %-3d | %.1f%n", num, stream.getMedian());
        }
    }
}
