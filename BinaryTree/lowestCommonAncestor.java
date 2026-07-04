public class lowestCommonAncestor {

    // Key insight: if root is p or q, it must be the LCA (even if the other is deeper in its subtree)
    // Recurse left and right — if both sides return non-null, root is the LCA
    // If only one side returns non-null, bubble that up
    public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);

        if (left != null && right != null)
            return root;   // p and q are on opposite sides

        return left != null ? left : right;
    }

    public static void main(String[] args) {
        //           3
        //          / \
        //         5   1
        //        / \ / \
        //       6  2 0  8
        //         / \
        //        7   4
        String[] input = {"3", "5", "1", "6", "2", "0", "8", "null", "null", "7", "4"};
        TreeNode root = TreeNode.constructTree(input);

        // Find actual node references after tree construction
        TreeNode p = root.left;          // node 5
        TreeNode q = root.left.right.right; // node 4

        TreeNode ancestor = lca(root, p, q);
        System.out.println("LCA of 5 and 4: " + ancestor.data); // Expected: 5

        TreeNode p2 = root.left;   // node 5
        TreeNode q2 = root.right;  // node 1
        TreeNode ancestor2 = lca(root, p2, q2);
        System.out.println("LCA of 5 and 1: " + ancestor2.data); // Expected: 3
    }
}
