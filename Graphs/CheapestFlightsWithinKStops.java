import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheapestFlightsWithinKStops {

    // Helper tuple to track structural state inside the queue
    static class QueueTuple {
        int stops;
        int node;
        int cost;

        public QueueTuple(int stops, int node, int cost) {
            this.stops = stops;
            this.node = node;
            this.cost = cost;
        }
    }

    // Helper neighbor representation class
    static class FlightNeighbor {
        int targetNode;
        int flightCost;

        public FlightNeighbor(int targetNode, int flightCost) {
            this.targetNode = targetNode;
            this.flightCost = flightCost;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Step 1: Build the directed adjacency list
        List<List<FlightNeighbor>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            int u = flight[0];
            int v = flight[1];
            int price = flight[2];
            adjList.get(u).add(new FlightNeighbor(v, price));
        }

        // Step 2: Initialize distance/cost tracking array
        int[] minCost = new int[n];
        Arrays.fill(minCost, (int) 1e9);
        minCost[src] = 0;

        // Step 3: Standard FIFO Queue tracking (stops, node, accumulated cost)
        Queue<QueueTuple> q = new LinkedList<>();
        // Seed the source airport: 0 stops, source node, 0 cost
        q.offer(new QueueTuple(0, src, 0));

        // Step 4: BFS Level-Order Traversal
        while (!q.isEmpty()) {
            QueueTuple curr = q.poll();
            int stops = curr.stops;
            int node = curr.node;
            int cost = curr.cost;

            // Pruning: If the current stop count reaches K + 1, skip expanding further
            if (stops > k) continue;

            for (FlightNeighbor neighbor : adjList.get(node)) {
                int adjNode = neighbor.targetNode;
                int flightCost = neighbor.flightCost;

                // Path Relaxation: Is this route cheaper than our recorded minimal cost?
                if (cost + flightCost < minCost[adjNode] && stops <= k) {
                    minCost[adjNode] = cost + flightCost;
                    q.offer(new QueueTuple(stops + 1, adjNode, cost + flightCost));
                }
            }
        }

        // Step 5: Return result (-1 if destination remains unreachable)
        return minCost[dst] == (int) 1e9 ? -1 : minCost[dst];
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops solver = new CheapestFlightsWithinKStops();

        int n = 4;
        int[][] flights = {
                {0, 1, 100},
                {1, 2, 100},
                {2, 0, 100},
                {1, 3, 600},
                {2, 3, 200}
        };
        int src = 0;
        int dst = 3;
        int k = 1;

        int cheapestPrice = solver.findCheapestPrice(n, flights, src, dst, k);
        System.out.println("Cheapest Flight Price within " + k + " stops: " + cheapestPrice);
        // Expected Output: 700 (Path: 0 -> 1 -> 3)
    }
}