import java.util.Collections;
import java.util.PriorityQueue;

// ─── 11. PriorityQueue → Heap in Java ────────────────────────────────────────
// Java's PriorityQueue is a MIN-heap by default.
// For MAX-heap: pass Collections.reverseOrder() as comparator.
public class PriorityQueueDemo {

    public static void main(String[] args) {

        // ── Min Heap ──────────────────────────────────────────────────────────
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(5);
        minHeap.offer(3);
        minHeap.offer(8);
        minHeap.offer(1);
        minHeap.offer(4);

        System.out.println("Min Heap peek (min): " + minHeap.peek());   // 1
        System.out.println("Poll (extract min):  " + minHeap.poll());   // 1
        System.out.println("Next min:            " + minHeap.peek());   // 3
        System.out.println("Size: " + minHeap.size());

        System.out.print("Drain min heap: ");
        while (!minHeap.isEmpty()) System.out.print(minHeap.poll() + " ");
        System.out.println();

        // ── Max Heap ──────────────────────────────────────────────────────────
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(5);
        maxHeap.offer(3);
        maxHeap.offer(8);
        maxHeap.offer(1);
        maxHeap.offer(4);

        System.out.println("\nMax Heap peek (max): " + maxHeap.peek());  // 8
        System.out.print("Drain max heap: ");
        while (!maxHeap.isEmpty()) System.out.print(maxHeap.poll() + " ");
        System.out.println();

        // ── Key API ───────────────────────────────────────────────────────────
        // offer(e)   → add element           O(log n)
        // poll()     → remove & return min   O(log n)
        // peek()     → view min, no remove   O(1)
        // remove(e)  → remove specific elem  O(n)
        // contains(e)                        O(n)
        // size()                             O(1)
    }
}
