import java.util.Stack;

public class twoSumBST {

    // Forward iterator — inorder (ascending)
    static class ForwardIterator {
        private final Stack<TreeNode> stack = new Stack<>();

        ForwardIterator(TreeNode root) { pushLeft(root); }

        private void pushLeft(TreeNode node) {
            while (node != null) { stack.push(node); node = node.left; }
        }

        public int next() {
            TreeNode node = stack.pop();
            pushLeft(node.right);
            return node.data;
        }

        public boolean hasNext() { return !stack.isEmpty(); }
    }

    // Reverse iterator — reverse inorder (descending)
    static class ReverseIterator {
        private final Stack<TreeNode> stack = new Stack<>();

        ReverseIterator(TreeNode root) { pushRight(root); }

        private void pushRight(TreeNode node) {
            while (node != null) { stack.push(node); node = node.right; }
        }

        public int prev() {
            TreeNode node = stack.pop();
            pushRight(node.left);
            return node.data;
        }

        public boolean hasPrev() { return !stack.isEmpty(); }
    }

    // Two-pointer approach: left pointer moves forward, right moves backward
    public static boolean twoSum(TreeNode root, int target) {
        ForwardIterator left = new ForwardIterator(root);
        ReverseIterator right = new ReverseIterator(root);

        int l = left.next();
        int r = right.prev();

        while (l < r) {
            int sum = l + r;
            if (sum == target) return true;
            else if (sum < target) l = left.next();
            else r = right.prev();
        }
        return false;
    }

    public static void main(String[] args) {
        //        5
        //       / \
        //      3   6
        //     / \   \
        //    2   4   7
        String[] input = {"5", "3", "6", "2", "4", "null", "7"};
        TreeNode root = TreeNode.constructTree(input);

        System.out.println("Target 9:  " + twoSum(root, 9));  // true  (2+7 or 3+6)
        System.out.println("Target 28: " + twoSum(root, 28)); // false
    }
}
