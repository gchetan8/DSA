public class Pair {
    int source;
    int dest;
    int weight; // Added property

    // Existing constructor (defaults weight to 1 for unit-weight graphs)
    public Pair(int s, int d) {
        this.source = s;
        this.dest = d;
        this.weight = 1;
    }

    // New overloaded constructor for weighted graphs (like Dijkstra)
    public Pair(int s, int d, int w) {
        this.source = s;
        this.dest = d;
        this.weight = w;
    }
}