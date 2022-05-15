import java.util.Stack;

public class SubTreeOfAnotherTree_572 {

    private class NodeStatus {
        TreeNode node;
        int visitedChildren;
        int size;

        public NodeStatus(TreeNode node) {
            this.node = node;
        }
    }

    private boolean preOrderCompare(TreeNode x, TreeNode y) {
        if (x == null && y == null) return true;
        else if (x == null || y == null) return false;
        else if (x.val != y.val) return false;
        else return preOrderCompare(x.left, y.left) && preOrderCompare(x.right, y.right);
    }

    public boolean isSubtreePreOrder(TreeNode root, TreeNode subRoot) {
        Stack<TreeNode> preOrderStack = new Stack<>();
        preOrderStack.push(root);
        while (!preOrderStack.isEmpty()) {
            TreeNode cur = preOrderStack.pop();
            if (preOrderCompare(cur, subRoot)) return true;
            if (cur.left != null) preOrderStack.push(cur.left);
            if (cur.right != null) preOrderStack.push(cur.right);
        }
        return false;
    }

    public boolean isSubtreeRecur(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (preOrderCompare(root, subRoot)) return true;
        return isSubtreeRecur(root.left, subRoot) || isSubtreeRecur(root.right, subRoot);
    }

    public boolean isSubtreeExtraMile(TreeNode root, TreeNode subRoot) {
        int subTreeSize = postOrderSize(subRoot);
        Stack<NodeStatus> stack = new Stack<>();
        stack.push(new NodeStatus(root));
        while (!stack.isEmpty()) {
            NodeStatus cur = stack.peek();
            if (cur.node == null) stack.pop();
            else if (cur.visitedChildren < 2) {
                stack.push(new NodeStatus(cur.visitedChildren++ == 0 ? cur.node.left : cur.node.right));
            } else { // both children already visited:
                if (++cur.size == subTreeSize && preOrderCompare(cur.node, subRoot)) return true;
                stack.pop(); // cur popped off
                if (!stack.isEmpty()) stack.peek().size += cur.size; // add this subtree size to its parent size
            }
        }
        return false;
    }

    private int postOrderSize(TreeNode node) {
        return node == null ? 0 : postOrderSize(node.left) + postOrderSize(node.right) + 1;
    }

    public static void main(String[] args) {
        SubTreeOfAnotherTree_572 solver = new SubTreeOfAnotherTree_572();
        TreeNode root = TreeNode.decodeStringToTree("[3,4,5,1,2,null,null,null,null,0]");
        TreeNode subRoot = TreeNode.decodeStringToTree("[4,1,2]");
        System.out.println(solver.isSubtreeExtraMile(root, subRoot));
    }
}
