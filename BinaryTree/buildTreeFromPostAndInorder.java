import java.util.HashMap;
import java.util.Map;

public class buildTreeFromPostAndInorder {

    static int postIdx;

    private static TreeNode build(int[] inorder, int[] postorder,
                                   int inStart, int inEnd,
                                   Map<Integer, Integer> inMap) {
        if (inStart > inEnd) return null;

        // Root is always the current last element of postorder
        int rootVal = postorder[postIdx--];
        TreeNode root = new TreeNode(rootVal);

        int mid = inMap.get(rootVal);

        // Build RIGHT before LEFT because postorder index moves right-to-left
        root.right = build(inorder, postorder, mid + 1, inEnd, inMap);
        root.left = build(inorder, postorder, inStart, mid - 1, inMap);

        return root;
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length - 1;

        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);

        return build(inorder, postorder, 0, inorder.length - 1, inMap);
    }

    public static void main(String[] args) {
        int[] inorder   = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = buildTree(inorder, postorder);
        levelOrderTraversal.printLevelWise(root);
        //       3
        //      / \
        //     9  20
        //       /  \
        //      15   7
    }
}
