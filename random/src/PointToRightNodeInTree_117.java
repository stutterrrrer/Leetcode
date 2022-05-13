import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PointToRightNodeInTree_117 {
    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    }

    private int level;
    private HashMap<Integer, Node> awaitingLeft;

    public Node connectConstantSpace(Node root) {
        for (Node linkedParent = root, leftmostOfNextLevel; ; linkedParent = leftmostOfNextLevel) {
            // 1. find the leftmost node of the next (unconnected) level
            while (linkedParent != null && linkedParent.left == null && linkedParent.right == null)
                linkedParent = linkedParent.next;
            if (linkedParent == null) break; // if there's no next level
            leftmostOfNextLevel = linkedParent.left != null ? linkedParent.left : linkedParent.right;
            // 2. now start connecting the next level:
            for (Node cur = leftmostOfNextLevel; ; cur = cur.next) { // makes one 'cur.next =' connection per iteration
                if (cur == linkedParent.left && linkedParent.right != null) { // 1. when cur.next = cur's sibling
                    cur.next = linkedParent.right;
                } else { // 2. when cur.next = cur's cousin
                    do linkedParent = linkedParent.next;
                    while (linkedParent != null && linkedParent.left == null && linkedParent.right == null);
                    if (linkedParent == null) break; // next level is all connected
                    cur.next = linkedParent.left != null ? linkedParent.left : linkedParent.right;
                }
            }
        }
        return root;
    }

    public Node connectBFS(Node root) {
        if (root == null) return null;
        Queue<Node> curLevel = new LinkedList<>();
        curLevel.offer(root);
        while (!curLevel.isEmpty()) {
            Queue<Node> nextLevel = new LinkedList<>();
            while (!curLevel.isEmpty()) {
                Node cur = curLevel.poll();
                cur.next = curLevel.peek();
                if (cur.left != null) nextLevel.offer(cur.left);
                if (cur.right != null) nextLevel.offer(cur.right);
            }
            curLevel = nextLevel;
        }
        return root;
    }

    public Node connectDFS(Node root) {
        level = -1;
        awaitingLeft = new HashMap<>();
        postOrder(root);
        return root;
    }

    private void postOrder(Node cur) {
        if (cur == null) return;
        level++;
        postOrder(cur.right);
        postOrder(cur.left);
        cur.next = awaitingLeft.get(level);
        awaitingLeft.put(level, cur);
        level--;
    }

    public Node connectDFSLocal(Node root) {
        HashMap<Integer, Node> waitingForLeft = new HashMap<>();
        postOrderLocal(root, 0, waitingForLeft);
        return root;
    }

    private void postOrderLocal(Node cur, int depth, HashMap<Integer, Node> waitingForLeft) {
        if (cur == null) return;
        postOrderLocal(cur.right, depth + 1, waitingForLeft);
        postOrderLocal(cur.left, depth + 1, waitingForLeft);
        cur.next = waitingForLeft.get(depth);
        waitingForLeft.put(depth, cur);
    }
}
