public class searchInBST {

    // BST property: left < root < right — halves the search space at every step
    public static TreeNode search(TreeNode root, int target) {
        if (root == null || root.data == target)
            return root;

        if (target < root.data)
            return search(root.left, target);
        else
            return search(root.right, target);
    }

    public static void main(String[] args) {
        //        4
        //       / \
        //      2   7
        //     / \
        //    1   3
        String[] input = {"4", "2", "7", "1", "3"};
        TreeNode root = TreeNode.constructTree(input);

        TreeNode found = search(root, 2);
        System.out.println("Search 2: " + (found != null ? found.data : "not found")); // 2

        TreeNode notFound = search(root, 5);
        System.out.println("Search 5: " + (notFound != null ? notFound.data : "not found")); // not found
    }
}
