public class inorderSuccessorBST {

    // Inorder successor = next node in sorted order (smallest node > given node)
    // Two cases:
    //   1. Node has right subtree  → leftmost node in right subtree
    //   2. No right subtree        → lowest ancestor where we last turned left
    // BST property handles both in a single pass — no need to separate the cases
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode target) {
        TreeNode successor = null;

        while (root != null) {
            if (target.data < root.data) {
                // root is a candidate successor; go left to find something smaller
                successor = root;
                root = root.left;
            } else {
                // root is <= target; successor must be on the right
                root = root.right;
            }
        }
        return successor;
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

        TreeNode target1 = root.left.right;          // node 4
        TreeNode succ1 = inorderSuccessor(root, target1);
        System.out.println("Successor of 4: " + (succ1 != null ? succ1.data : "none")); // 5

        TreeNode target2 = root.left.right.right;    // node 5
        TreeNode succ2 = inorderSuccessor(root, target2);
        System.out.println("Successor of 5: " + (succ2 != null ? succ2.data : "none")); // 6

        TreeNode target3 = root.right.right;         // node 9 (largest)
        TreeNode succ3 = inorderSuccessor(root, target3);
        System.out.println("Successor of 9: " + (succ3 != null ? succ3.data : "none")); // none
    }
}
