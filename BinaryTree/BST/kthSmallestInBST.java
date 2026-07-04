public class kthSmallestInBST {

    // Inorder of BST is sorted — the Kth node visited is the Kth smallest
    static int count = 0;
    static int result = -1;

    private static void inorder(TreeNode root, int k) {
        if (root == null || count >= k) return;

        inorder(root.left, k);

        count++;
        if (count == k) {
            result = root.data;
            return;
        }

        inorder(root.right, k);
    }

    public static int kthSmallest(TreeNode root, int k) {
        count = 0;
        result = -1;
        inorder(root, k);
        return result;
    }

    public static void main(String[] args) {
        //        5
        //       / \
        //      3   6
        //     / \
        //    2   4
        //   /
        //  1
        String[] input = {"5", "3", "6", "2", "4", "null", "null", "1"};
        TreeNode root = TreeNode.constructTree(input);

        System.out.println("1st smallest: " + kthSmallest(root, 1)); // 1
        System.out.println("3rd smallest: " + kthSmallest(root, 3)); // 3
        System.out.println("5th smallest: " + kthSmallest(root, 5)); // 5
    }
}
