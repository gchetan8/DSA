import java.util.ArrayList;
import java.util.List;

public class DisjointSet {

    private List<Integer> parent;
    private List<Integer> rank;
    private List<Integer> size;

    // Constructor handles both 0-indexed and 1-indexed graph inputs up to size n
    public DisjointSet(int n) {
        parent = new ArrayList<>();
        rank = new ArrayList<>();
        size = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            parent.add(i); // Each node starts as its own parent
            rank.add(0);    // Initial height/rank is 0
            size.add(1);    // Initial component size is 1
        }
    }

    // Path Compression Find Operation
    public int findParent(int node) {
        if (node == parent.get(node)) {
            return node;
        }
        // Path compression step
        int ultimateParent = findParent(parent.get(node));
        parent.set(node, ultimateParent);
        return parent.get(node);
    }

    // 1. UNION BY RANK
    public void unionByRank(int u, int v) {
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        // Already in the same component
        if (ulp_u == ulp_v) return;

        if (rank.get(ulp_u) < rank.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
        } else if (rank.get(ulp_v) < rank.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
        } else {
            // Equal rank case: attach v under u, increment rank of u
            parent.set(ulp_v, ulp_u);
            rank.set(ulp_u, rank.get(ulp_u) + 1);
        }
    }

    // 2. UNION BY SIZE
    public boolean unionBySize(int u, int v) {
        int ulp_u = findParent(u);
        int ulp_v = findParent(v);

        // Already in the same component
        if (ulp_u == ulp_v) return false;
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
        return true;
    }

    // Helper method to retrieve size of component containing node u
    public int getSize(int node) {
        return size.get(findParent(node));
    }

    public static void main(String[] args) {
        int n = 7;
        DisjointSet ds = new DisjointSet(n);

        // Testing Union by Size
        ds.unionBySize(1, 2);
        ds.unionBySize(2, 3);
        ds.unionBySize(4, 5);
        ds.unionBySize(6, 7);
        ds.unionBySize(5, 6);

        // Check if node 3 and 7 are in the same component
        if (ds.findParent(3) == ds.findParent(7)) {
            System.out.println("Node 3 and 7 belong to the SAME component.");
        } else {
            System.out.println("Node 3 and 7 belong to DIFFERENT components.");
        }

        // Connect components (3 and 7)
        ds.unionBySize(3, 7);

        if (ds.findParent(3) == ds.findParent(7)) {
            System.out.println("Node 3 and 7 NOW belong to the SAME component.");
        }

        System.out.println("Total size of component containing node 1: " + ds.getSize(1));
    }
}