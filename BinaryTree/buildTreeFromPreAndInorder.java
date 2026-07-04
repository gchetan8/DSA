import java.util.HashMap;
import java.util.Map;

public class buildTreeFromPreAndInorder {

    static int preIdx;

    private static TreeNode build(int[] inorder, int[] preorder,
                                   int inStart, int inEnd,
                                   Map<Integer, Integer> inMap) {
        if (inStart > inEnd) return null;

        // Root is always the current first element of preorder
        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);

        int mid = inMap.get(rootVal);

        // Build LEFT before RIGHT because preorder index moves left-to-right
        root.left = build(inorder, preorder, inStart, mid - 1, inMap);
        root.right = build(inorder, preorder, mid + 1, inEnd, inMap);

        return root;
    }

    public static TreeNode buildTree(int[] inorder, int[] preorder) {
        preIdx = 0;

        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            inMap.put(inorder[i], i);

        return build(inorder, preorder, 0, inorder.length - 1, inMap);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder  = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(inorder, preorder);
        levelOrderTraversal.printLevelWise(root);
        //       3
        //      / \
        //     9  20
        //       /  \
        //      15   7
    }
}
