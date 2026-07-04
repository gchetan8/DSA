import java.util.ArrayList;
import java.util.List;

public class boundaryView {

    private static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    // Left boundary top-down, excluding leaf
    private static void addLeftBoundary(TreeNode root, List<Integer> result) {
        TreeNode cur = root.left;
        while (cur != null) {
            if (!isLeaf(cur)) result.add(cur.data);
            cur = cur.left != null ? cur.left : cur.right;
        }
    }

    // All leaves left-to-right via DFS
    private static void addLeaves(TreeNode root, List<Integer> result) {
        if (root == null) return;
        if (isLeaf(root)) {
            result.add(root.data);
            return;
        }
        addLeaves(root.left, result);
        addLeaves(root.right, result);
    }

    // Right boundary bottom-up, excluding leaf
    private static void addRightBoundary(TreeNode root, List<Integer> result) {
        TreeNode cur = root.right;
        List<Integer> temp = new ArrayList<>();
        while (cur != null) {
            if (!isLeaf(cur)) temp.add(cur.data);
            cur = cur.right != null ? cur.right : cur.left;
        }
        // Add in reverse so boundary goes bottom-up
        for (int i = temp.size() - 1; i >= 0; i--)
            result.add(temp.get(i));
    }

    public static List<Integer> boundaryView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        result.add(root.data);
        addLeftBoundary(root, result);
        addLeaves(root, result);
        addRightBoundary(root, result);
        return result;
    }

    public static void main(String[] args) {
        //           1
        //          / \
        //         2   3
        //        / \ / \
        //       4  5 6  7
        String[] input = {"1", "2", "3", "4", "5", "6", "7"};
        TreeNode root = TreeNode.constructTree(input);
        System.out.println(boundaryView(root));
        // Expected: [1, 2, 4, 5, 6, 7, 3]
    }
}
