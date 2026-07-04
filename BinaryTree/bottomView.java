import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class bottomView {

    static class Pair {
        TreeNode node;
        int col;
        Pair(TreeNode node, int col) {
            this.node = node;
            this.col = col;
        }
    }

    public static List<Integer> bottomView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // TreeMap keeps columns sorted; overwrite each time so the LAST node per column wins (BFS = top-down, so last = bottom)
        Map<Integer, Integer> colMap = new TreeMap<>();

        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0));

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            colMap.put(curr.col, curr.node.data);

            if (curr.node.left != null) q.offer(new Pair(curr.node.left, curr.col - 1));
            if (curr.node.right != null) q.offer(new Pair(curr.node.right, curr.col + 1));
        }

        result.addAll(colMap.values());
        return result;
    }

    public static void main(String[] args) {
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        String[] input = {"1", "2", "3", "4", "5"};
        TreeNode root = TreeNode.constructTree(input);
        System.out.println(bottomView(root));
        // Expected: [4, 2, 5, 3]
    }
}
