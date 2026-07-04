import java.util.PriorityQueue;

// Problem 2: Buy maximum items with a given sum
// Given costs of items and a budget, buy as many items as possible.
//
// Approach: Greedily pick cheapest items using a min-heap.
// Time: O(n log n)  |  Space: O(n)
public class BuyMaximumItems {

    public static int maxItems(int[] costs, int budget) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int cost : costs) minHeap.offer(cost);

        int count = 0;
        while (!minHeap.isEmpty() && budget >= minHeap.peek()) {
            budget -= minHeap.poll();
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] costs = {1, 12, 5, 111, 200, 1000, 10, 9, 3};
        int budget = 50;
        System.out.println("Costs:  " + java.util.Arrays.toString(costs));
        System.out.println("Budget: " + budget);
        System.out.println("Max items bought: " + maxItems(costs, budget));
        // Sorted: [1,3,5,9,10,12,...] → buy 1+3+5+9+10=28, then 12→40, stop at 111
        // Answer: 6
    }
}
