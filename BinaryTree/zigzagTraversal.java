import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class zigzagTraversal {

    public static List<List<Integer>> zigzag(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.data);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            if (!leftToRight)
                Collections.reverse(level);

            result.add(level);
            leftToRight = !leftToRight;
        }
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
        System.out.println(zigzag(root));
        // Expected: [[3], [20, 9], [15, 7]]
    }
}
