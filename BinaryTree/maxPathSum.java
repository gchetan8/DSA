public class maxPathSum {

    static int globalMax;

    // Returns the max gain this node can contribute to its parent (single branch only)
    // But updates globalMax with the best path passing through this node (both branches)
    private static int maxGain(TreeNode root) {
        if (root == null)
            return 0;

        // Ignore negative contributions — better to not include that branch
        int left = Math.max(0, maxGain(root.left));
        int right = Math.max(0, maxGain(root.right));

        // Best path through this node uses both left and right
        globalMax = Math.max(globalMax, root.data + left + right);

        // Can only pick one branch when returning to parent
        return root.data + Math.max(left, right);
    }

    public static int maxPathSum(TreeNode root) {
        globalMax = Integer.MIN_VALUE;
        maxGain(root);
        return globalMax;
    }

    public static void main(String[] args) {
        //      1
        //     / \
        //    2   3
        String[] input1 = {"1", "2", "3"};
        TreeNode root1 = TreeNode.constructTree(input1);
        System.out.println("Max Path Sum: " + maxPathSum(root1)); // Expected: 6

        //       -10
        //       /  \
        //      9   20
        //         /  \
        //        15   7
        String[] input2 = {"-10", "9", "20", "null", "null", "15", "7"};
        TreeNode root2 = TreeNode.constructTree(input2);
        System.out.println("Max Path Sum: " + maxPathSum(root2)); // Expected: 42 (15->20->7)
    }
}
