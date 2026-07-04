public class diameterOfBinaryTree {

    static int maxDiameter = 0;

    // At each node, diameter through it = left height + right height
    // Reuse the height calculation — no second pass needed
    private static int height(TreeNode root) {
        if (root == null)
            return 0;

        int left = height(root.left);
        int right = height(root.right);

        maxDiameter = Math.max(maxDiameter, left + right);

        return 1 + Math.max(left, right);
    }

    public static int diameter(TreeNode root) {
        maxDiameter = 0;
        height(root);
        return maxDiameter;
    }

    public static void main(String[] args) {
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        String[] input = {"1", "2", "3", "4", "5"};
        TreeNode root = TreeNode.constructTree(input);
        System.out.println("Diameter: " + diameter(root)); // Expected: 3 (4->2->5 or 4->2->1->3)
    }
}
