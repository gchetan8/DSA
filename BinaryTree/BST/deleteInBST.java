public class deleteInBST {

    // Find the leftmost node in the right subtree (inorder successor)
    private static int inorderSuccessor(TreeNode node) {
        int val = node.data;
        while (node.left != null) {
            val = node.left.data;
            node = node.left;
        }
        return val;
    }

    public static TreeNode delete(TreeNode root, int val) {
        if (root == null)
            return null;

        if (val < root.data) {
            root.left = delete(root.left, val);
        } else if (val > root.data) {
            root.right = delete(root.right, val);
        } else {
            // Node to delete found — three cases:

            // Case 1: leaf node
            if (root.left == null && root.right == null)
                return null;

            // Case 2: one child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 3: two children — replace with inorder successor, then delete it from right subtree
            int successor = inorderSuccessor(root.right);
            root.data = successor;
            root.right = delete(root.right, successor);
        }
        return root;
    }

    public static void main(String[] args) {
        //        5
        //       / \
        //      3   6
        //     / \   \
        //    2   4   7
        String[] input = {"5", "3", "6", "2", "4", "null", "7"};
        TreeNode root = TreeNode.constructTree(input);

        root = delete(root, 3); // two children — replaced by inorder successor (4)
        inOrderTraversal.printInOrder(root); // 2 4 5 6 7

        root = delete(root, 6); // one child
        inOrderTraversal.printInOrder(root); // 2 4 5 7

        root = delete(root, 2); // leaf
        inOrderTraversal.printInOrder(root); // 4 5 7
    }
}
