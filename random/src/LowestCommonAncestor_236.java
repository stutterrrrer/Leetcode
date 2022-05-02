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
        NodeStatus cur = new NodeStatus(root);
        while (true) {
            if (cur.node == null) cur = stack.pop(); // base case 1

            if (cur.visitedSubtrees < 2) { // go to left & right subtrees first
                cur.visitedSubtrees++;
                stack.push(cur);
                cur = new NodeStatus(cur.visitedSubtrees == 1 ? cur.node.left : cur.node.right);
                continue;
            }
            // if (cur.visitedSubtrees == 2): base case 2 - add this subtree's #of found descendants to the parent's.
            if (cur.node == p || cur.node == q) cur.descendants++;
            if (cur.descendants == 2) return cur.node; // return before traversing whole tree; beats recursion
            stack.peek().descendants += cur.descendants; // if cur is ancestor, cur's parent is also an ancestor
            cur = stack.pop();
        }
    }

    private static class Node3Stages {
        public TreeNode node;
        public int visitedSubTrees;

        public Node3Stages(TreeNode node, int visitedSubTrees) {
            this.node = node;
            this.visitedSubTrees = visitedSubTrees;
        }
    }

    public TreeNode lowestCommonAncestorCleverStack(TreeNode root, TreeNode p, TreeNode q) {
        int found = 0, pVal = p.val, qVal = q.val;
        TreeNode lowestAncestorToFirst = root;
        Stack<Node3Stages> stack = new Stack<>(); // Integer: #of visited subtrees of this node
        Node3Stages cur = new Node3Stages(root, 0);
        while (true) {
            if (cur.node == null) cur = stack.pop();
            // first time reaching this node (not after coming back up from its children)
            if (cur.visitedSubTrees == 0 && (cur.node.val == pVal || cur.node.val == qVal))
                if (++found == 1) lowestAncestorToFirst = cur.node;
            if (found == 2) { // no need to go further down to subtrees - common ancestor is above
                while (cur.node != lowestAncestorToFirst) cur = stack.pop();
                return cur.node;
            } else { // if found < 2:
                if (cur.visitedSubTrees < 2) { // visit subtrees, if it has subtrees.
                    stack.push(cur);
                    cur = new Node3Stages(++cur.visitedSubTrees == 1 ? cur.node.left : cur.node.right, 0);
                    continue;
                }
                // if both of the subtrees of curNode has been visited:
                if (found == 1 && cur.node == lowestAncestorToFirst)
                    lowestAncestorToFirst = stack.peek().node;
                cur = stack.pop();
            }
        }
    }

    public void postOrderPrint(TreeNode root) {
        Stack<Node3Stages> stack = new Stack<>();
        stack.push(new Node3Stages(root, 0));
        while (!stack.isEmpty()) {
            Node3Stages cur = stack.pop();
            if (cur.node == null) continue;
            if (cur.visitedSubTrees < 2) {
                stack.push(cur);
                stack.push(new Node3Stages(
                        cur.visitedSubTrees++ == 0 ? cur.node.left : cur.node.right
                        , 0));
            } else System.out.print(cur.node.val + ", ");
        }
    }

    public TreeNode testHelper(TreeNode root, int pVal, int qVal) {
        TreeNode p = new TreeNode(pVal);
        TreeNode q = new TreeNode(qVal);
        return lowestCommonAncestorCleverStack(root, p, q);
    }

    public static void main(String[] args) {
        TreeNode root = ConstructTree.constructTree("[3,5,1,6,2,0,8,null,null,7,4]");
//        int pVal = 5, qVal = 1;
        LowestCommonAncestor_236 solver = new LowestCommonAncestor_236();
//        System.out.println(solver.testHelper(root, pVal, qVal).val);
        solver.postOrderPrint(root);
    }
}
