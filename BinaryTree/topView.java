import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class topView {

    static class Pair {
        TreeNode node;
        int col;
        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public static List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // TreeMap keeps columns sorted; only store the FIRST node seen per column (BFS = top-down)
        Map<Integer, Integer> colMap = new TreeMap<>();

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            int col = curr.col;

            if (!colMap.containsKey(col))
                colMap.put(col, curr.node.data);

            if (curr.node.left != null) q.offer(new Pair(curr.node.left, col - 1));
            if (curr.node.right != null) q.offer(new Pair(curr.node.right, col + 1));
        }

        result.addAll(colMap.values());
        return result;
    }

    public static void main(String[] args) {
        //        1
        //       / \
        //      2   3
        //       \
        //        4
        //         \
        //          5
        //           \
        //            6
        String[] input = {"1", "2", "3", "null", "4", "null", "null", "null", "5", "null", "6"};
        TreeNode root = TreeNode.constructTree(input);
        System.out.println(topView(root));
        // Expected: [2, 1, 3, 6]
    }
}
