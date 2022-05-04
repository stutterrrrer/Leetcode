import java.util.Stack;

public class KthSmallestInBST_230 {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> nodesWithBiggerValues = new Stack<>();
        while (true) {
            if (root != null) {
                nodesWithBiggerValues.push(root); // visit this node later
                root = root.left; // go to the smaller node first
            } else { // when cur == null -> the top of the stack is the smallest remaining node.
                if (nodesWithBiggerValues.isEmpty()) break; // all nodes have been visited.
                // "visit" the smallest of the remaining nodes:
                root = nodesWithBiggerValues.pop();
                if (--k == 0) return root.val;
                root = root.right; // there's no smaller node than root - go to the next node that's bigger than root
            }
        }
        return -1;
    }

    public void inOrderPrint(TreeNode root) {
        Stack<TreeNode> nodesWithBiggerValues = new Stack<>();
        while (true) {
            if (root != null) {
                nodesWithBiggerValues.push(root); // visit this node later
                root = root.left; // go to the smaller node first
            } else { // when cur == null -> top of the stack is the smallest remaining node
                if (nodesWithBiggerValues.isEmpty()) break; //all nodes have been visited;

                // "visit" the smallest of the remaining nodes:
                root = nodesWithBiggerValues.pop();
                System.out.print(root.val + ", ");
                // there's no smaller node than root, so go to next node, which is bigger than root
                root = root.right;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.decodeStringToTree("[3,1,4,null,2]");
        int rank = 4;
        KthSmallestInBST_230 solver = new KthSmallestInBST_230();
        solver.inOrderPrint(root);
    }
}
