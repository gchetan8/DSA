public class isIdentical {

    public static boolean isIdentical(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;
        if (p.data != q.data)
            return false;

        return isIdentical(p.left, q.left) && isIdentical(p.right, q.right);
    }

    public static void main(String[] args) {
        //    1          1
        //   / \        / \
        //  2   3      2   3
        String[] input1 = {"1", "2", "3"};
        String[] input2 = {"1", "2", "3"};
        TreeNode root1 = TreeNode.constructTree(input1);
        TreeNode root2 = TreeNode.constructTree(input2);
        System.out.println("Identical: " + isIdentical(root1, root2)); // true

        //    1          1
        //   / \        / \
        //  2   3      2   4
        String[] input3 = {"1", "2", "4"};
        TreeNode root3 = TreeNode.constructTree(input3);
        System.out.println("Identical: " + isIdentical(root1, root3)); // false
    }
}
