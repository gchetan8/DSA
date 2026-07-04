public class isSymmetrical {

    // A tree is symmetrical if its left and right subtrees are mirrors of each other
    private static boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.data != right.data) return false;

        // Outer pair and inner pair must both match
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public static boolean isSymmetrical(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public static void main(String[] args) {
        //      1
        //     / \
        //    2   2
        //   / \ / \
        //  3  4 4  3
        String[] sym = {"1", "2", "2", "3", "4", "4", "3"};
        TreeNode root1 = TreeNode.constructTree(sym);
        System.out.println("Symmetrical: " + isSymmetrical(root1)); // true

        //      1
        //     / \
        //    2   2
        //     \   \
        //      3   3
        String[] notSym = {"1", "2", "2", "null", "3", "null", "3"};
        TreeNode root2 = TreeNode.constructTree(notSym);
        System.out.println("Symmetrical: " + isSymmetrical(root2)); // false
    }
}
