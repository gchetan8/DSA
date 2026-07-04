import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class verticalOrderTraversal {

    // Pair to carry node + its column index through BFS
    static class Pair {
        TreeNode node;
        int col;
        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // TreeMap keeps columns sorted left to right automatically
        Map<Integer, List<Integer>> colMap = new TreeMap<>();

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            TreeNode node = curr.node;
            int col = curr.col;

            colMap.computeIfAbsent(col, k -> new ArrayList<>()).add(node.data);

            if (node.left != null) q.offer(new Pair(node.left, col - 1));
            if (node.right != null) q.offer(new Pair(node.right, col + 1));
        }

        result.addAll(colMap.values());
        return result;
    }

    public static void main(String[] args) {
        //        3
        //       / \
        //      9  20
        //        /  \
        //       15   7
        String[] input = {"3", "9", "20", "null", "null", "15", "7"};
        TreeNode root = TreeNode.constructTree(input);
        System.out.println(verticalOrder(root));
        // Expected: [[9], [3, 15], [20], [7]]
    }
}
