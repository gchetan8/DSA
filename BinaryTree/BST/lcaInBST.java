public class lcaInBST {

    // BST property lets us direct the search — no need to explore both sides blindly
    public static TreeNode lca(TreeNode root, int p, int q) {
        while (root != null) {
            if (p < root.data && q < root.data)
                root = root.left;   // both nodes in left subtree
            else if (p > root.data && q > root.data)
                root = root.right;  // both nodes in right subtree
            else
                return root;        // nodes split across root, or one equals root — this is the LCA
        }
        return null;
    }

    public static void main(String[] args) {
        //        6
        //       / \
        //      2   8
        //     / \ / \
        //    0  4 7  9
        //      / \
        //     3   5
        String[] input = {"6", "2", "8", "0", "4", "7", "9", "null", "null", "3", "5"};
        TreeNode root = TreeNode.constructTree(input);

        System.out.println("LCA(2,8): " + lca(root, 2, 8).data); // 6
        System.out.println("LCA(2,4): " + lca(root, 2, 4).data); // 2
        System.out.println("LCA(3,5): " + lca(root, 3, 5).data); // 4
    }
}
