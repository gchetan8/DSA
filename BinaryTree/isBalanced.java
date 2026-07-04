public class isBalanced {

    // Returns -1 if subtree is unbalanced, otherwise returns its height
    // This avoids a second pass — height and balance check happen together
    private static int checkHeight(TreeNode root) {
        if (root == null)
            return 0;

        int left = checkHeight(root.left);
        if (left == -1) return -1;

        int right = checkHeight(root.right);
        if (right == -1) return -1;

        if (Math.abs(left - right) > 1)
            return -1;

        return 1 + Math.max(left, right);
    }

    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    public static void main(String[] args) {
        //      3
        //     / \
        //    9  20
        //      /  \
        //     15   7
        String[] balanced = {"3", "9", "20", "null", "null", "15", "7"};
        TreeNode root1 = TreeNode.constructTree(balanced);
        System.out.println("Balanced: " + isBalanced(root1)); // true

        //       1
        //      / \
        //     2   2
        //    / \
        //   3   3
        //  / \
        // 4   4
        String[] unbalanced = {"1", "2", "2", "3", "3", "null", "null", "4", "4"};
        TreeNode root2 = TreeNode.constructTree(unbalanced);
        System.out.println("Balanced: " + isBalanced(root2)); // false
    }
}
