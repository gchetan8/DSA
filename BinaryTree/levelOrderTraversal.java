import java.util.LinkedList;
import java.util.Queue;

public class levelOrderPrint {

    public static void printLevelWise(TreeNode root){
        if(root == null)
            return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                TreeNode node = q.poll();
                System.out.print(node.data + " ");
                if(node.left != null)
                    q.offer(node.left);
                if(node.right != null)
                    q.offer(node.right);
            }
            System.out.println(); // Prints a new line per tree level
        }
    }

    public static void main(String[] args) {
        String[] input = {"3", "9" , "20" , "null", "null", "15", "7"};
        TreeNode root = TreeNode.constructTree(input);
        printLevelWise(root);
    }
}