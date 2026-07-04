public class recoverBST {

    // Inorder of a correct BST is strictly sorted.
    // Two swapped nodes create 1 or 2 inversions (prev.data > curr.data):
    //   - Non-adjacent swap: 2 inversions — first node of 1st, second node of 2nd
    //   - Adjacent swap:     1 inversion  — both nodes are in that single inversion

    static TreeNode first, middle, second, prev;

    private static void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        if (prev != null && prev.data > root.data) {
            if (first == null) {
                first = prev;       // first violation: mark both ends
                middle = root;      // middle handles the adjacent-swap case
            } else {
                second = root;      // second violation: update the right end
            }
        }
        prev = root;

        inorder(root.right);
    }

    public static void recover(TreeNode root) {
        first = middle = second = prev = null;
        inorder(root);

        // Swap values back
        if (second != null)
            swap(first, second);    // non-adjacent swap
        else
            swap(first, middle);    // adjacent swap
    }

    private static void swap(TreeNode a, TreeNode b) {
        int temp = a.data;
        a.data = b.data;
        b.data = temp;
    }

    public static void main(String[] args) {
        // Case 1: non-adjacent nodes swapped (3 and 6 swapped)
        //        6            3
        //       / \    →     / \
        //      3   8        6   8   ← original correct BST
        //     / \          / \
        //    2   4        2   4
        String[] input1 = {"6", "3", "8", "2", "4"};
        TreeNode root1 = TreeNode.constructTree(input1);
        System.out.print("Before: "); inOrderTraversal.printInOrder(root1); // 2 3 4 6 8 — wrong
        recover(root1);
        System.out.print("After:  "); inOrderTraversal.printInOrder(root1); // 2 3 4 6 8 — correct

        System.out.println();

        // Case 2: adjacent nodes swapped (2 and 3 swapped)
        //        3            3
        //       / \    →     / \
        //      2   4        2   4   ← original correct BST
        //     /            /
        //    1            1
        String[] input2 = {"3", "2", "4", "1"};
        TreeNode root2 = TreeNode.constructTree(input2);

        // manually swap adjacent nodes 2 and 3 to simulate the broken BST
        TreeNode brokenRoot = new TreeNode(2);
        brokenRoot.left = new TreeNode(3);
        brokenRoot.left.left = new TreeNode(1);
        brokenRoot.right = new TreeNode(4);

        System.out.print("Before: "); inOrderTraversal.printInOrder(brokenRoot); // 1 3 2 4 — wrong
        recover(brokenRoot);
        System.out.print("After:  "); inOrderTraversal.printInOrder(brokenRoot); // 1 2 3 4 — correct
    }
}
