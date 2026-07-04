public class maxDepth {

    // Recursive DFS: depth = 1 + max(left depth, right depth)
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    public static void main(String[] args) {
        //        3
        //       / \
        //      9  20
        //        /  \
        //       15   7
        String[] input = {"3", "9", "20", "null", "null", "15", "7"};
        TreeNode root = TreeNode.constructTree(input);
        System.out.println("Max Depth: " + maxDepth(root)); // Expected: 3
    }
}
