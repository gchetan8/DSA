package BST;

public class ceilInBST {

    // Ceil: smallest value in BST >= target
    public static int ceil(TreeNode root, int target) {
        int ceil = -1;

        while (root != null) {
            if (root.data == target)
                return root.data;

            if (target < root.data) {
                // Current node is a candidate ceil; go left to find something smaller
                ceil = root.data;
                root = root.left;
            } else {
                // Current node is too small; go right
                root = root.right;
            }
        }
        return ceil;
    }

    public static void main(String[] args) {
        //        8
        //       / \
        //      4  12
        //     / \ / \
        //    2  6 10 14
        String[] input = {"8", "4", "12", "2", "6", "10", "14"};
        TreeNode root = TreeNode.constructTree(input);

        System.out.println("Ceil(5):  " + ceil(root, 5));  // 6
        System.out.println("Ceil(6):  " + ceil(root, 6));  // 6 (exact match)
        System.out.println("Ceil(11): " + ceil(root, 11)); // 12
        System.out.println("Ceil(14): " + ceil(root, 14)); // 14
        System.out.println("Ceil(15): " + ceil(root, 15)); // -1 (no ceil exists)
    }
}
