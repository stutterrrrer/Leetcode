import java.util.Stack;

public class LowestCommonAncestor_236 {
    private TreeNode lowestCommonAncestor;

    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        containsTargetPostOrder(root, p, q);
        return this.lowestCommonAncestor;
    }

    private boolean containsTargetPostOrder(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        int descendants = 0;

        if (containsTargetPostOrder(root.left, p, q)) descendants++;
        if (containsTargetPostOrder(root.right, p, q)) descendants++;
        if (root == p || root == q) descendants++;

        // the first node (in post-order-traversal) that has both targets as descendants is the lowest common ancestor
        if (descendants == 2 && lowestCommonAncestor == null) lowestCommonAncestor = root;
        return descendants > 0;
    }

    private static class NodeStatus {
        TreeNode node;
        int visitedSubtrees = 0;
        int descendants = 0;

        public NodeStatus(TreeNode node) {
            this.node = node;
        }
    }

    public TreeNode lowestCommonAncestorIntuitiveStack(TreeNode root, TreeNode p, TreeNode q) {
        Stack<NodeStatus> stack = new Stack<>();
        stack.push(new NodeStatus(root));
        while (!stack.isEmpty()) {
            NodeStatus cur = stack.peek();
            if (cur.node == null) {
                stack.pop(); // "return" to parent
            } else if (cur.visitedSubtrees < 2) { // go to left & right subtrees first
                stack.push(new NodeStatus(cur.visitedSubtrees++ == 0 ? cur.node.left : cur.node.right));
            } else { // if (cur.visitedSubtrees == 2): add this subtree's #of found descendants to the parent's.
                if (cur.node.val == p.val || cur.node.val == q.val) cur.descendants++;
                if (cur.descendants == 2) return cur.node; // return before traversing whole tree; beats recursion
                stack.pop(); // "return" to parent
                stack.peek().descendants += cur.descendants; // if cur is ancestor, cur's parent is also an ancestor
            }
        }
        return null;
    }

    private static class Node3Stages {
        public TreeNode node;
        public int visitedSubTrees;

        public Node3Stages(TreeNode node) {
            this.node = node;
        }
    }

    public TreeNode lowestCommonAncestorCleverStack(TreeNode root, TreeNode p, TreeNode q) {
        int found = 0, pVal = p.val, qVal = q.val;
        TreeNode lowestAncestorOnStacToFirstFound = root;
        Stack<Node3Stages> stack = new Stack<>(); // Integer: #of visited subtrees of this node
        stack.push(new Node3Stages(root));
        while (!stack.isEmpty()) {
            Node3Stages cur = stack.peek();
            if (cur.node == null) {
                stack.pop();
                continue;
            }
            // if cur.node != null:
            if (cur.visitedSubTrees == 0 && (cur.node.val == pVal || cur.node.val == qVal))
                // first time reaching this node (not after coming back up from its children)
                if (++found == 1) lowestAncestorOnStacToFirstFound = cur.node;

            if (found == 2) { // no need to go further down to subtrees - common ancestor is above
                return lowestAncestorOnStacToFirstFound; // because any node still on stack is cur's ancestor
            } else { // if found < 2:
                if (cur.visitedSubTrees < 2) { // visit subtrees
                    stack.push(new Node3Stages(++cur.visitedSubTrees == 1 ? cur.node.left : cur.node.right));
                } else { // if both of the subtrees of curNode has been visited:
                    stack.pop(); // "return" to cur's parent - by popping off cur
                    if (found == 1 && cur.node == lowestAncestorOnStacToFirstFound)
                        lowestAncestorOnStacToFirstFound = stack.peek().node; // because all nodes on the stack is cur.node's ancestor
                }
            }
        }
        return null;
    }

    public void postOrderPrint(TreeNode root) {
        Stack<Node3Stages> stack = new Stack<>();
        stack.push(new Node3Stages(root));
        while (!stack.isEmpty()) {
            Node3Stages cur = stack.peek(); // peek()! not pop()
            if (cur.node == null) {
                stack.pop(); // "return" to parent
            } else if (cur.visitedSubTrees < 2) { // "recursively call" on the left / right subtree
                stack.push(new Node3Stages(
                        cur.visitedSubTrees++ == 0 ? cur.node.left : cur.node.right));
            } else { // print and "return" to parent
                System.out.print(cur.node.val + ", ");
                stack.pop();
            }
        }
    }

    public TreeNode testHelper(TreeNode root, int pVal, int qVal) {
        TreeNode p = new TreeNode(pVal);
        TreeNode q = new TreeNode(qVal);
        return lowestCommonAncestorCleverStack(root, p, q);
    }

    public static void main(String[] args) {
        TreeNode root = ConstructTree.constructTree("[3,5,1,6,2,0,8,null,null,7,4]");
        int pVal = 5, qVal = 1;
        LowestCommonAncestor_236 solver = new LowestCommonAncestor_236();
        System.out.println(solver.testHelper(root, pVal, qVal).val);
    }
}
