public class insertInBST {

    // Always inserts as a new leaf — no restructuring needed
    public static TreeNode insert(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        if (val < root.data)
            root.left = insert(root.left, val);
        else if (val > root.data)
            root.right = insert(root.right, val);
        // val == root.data: duplicate, do nothing

        return root;
    }

    public static void main(String[] args) {
        //        4
        //       / \
        //      2   7
        //     / \
        //    1   3
        String[] input = {"4", "2", "7", "1", "3"};
        TreeNode root = TreeNode.constructTree(input);

        root = insert(root, 5);

        //        4
        //       / \
        //      2   7
        //     / \ /
        //    1  3 5
        inOrderTraversal.printInOrder(root); // 1 2 3 4 5 7
    }
}
