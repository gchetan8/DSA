import java.util.Stack;

public class BSTIterator {

    // Stack holds the next nodes to visit in inorder (left -> root -> right)
    // We push only the leftmost path at a time — O(h) space instead of O(n)
    private final Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        pushLeft(root);
    }

    // Pushes all left nodes from the given node down to the leftmost leaf
    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    // Returns the next smallest element — O(1) amortized
    public int next() {
        TreeNode node = stack.pop();
        // If this node has a right subtree, its leftmost path is the next inorder sequence
        pushLeft(node.right);
        return node.data;
    }

    // O(1)
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public static void main(String[] args) {
        //        7
        //       / \
        //      3  15
        //        /  \
        //        9  20
        String[] input = {"7", "3", "15", "null", "null", "9", "20"};
        TreeNode root = TreeNode.constructTree(input);

        BSTIterator iterator = new BSTIterator(root);

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " "); // 3 7 9 15 20
        }
        System.out.println();
    }
}
