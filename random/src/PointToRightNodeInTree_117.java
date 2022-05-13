import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PointToRightNodeInTree_117 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
    }

    private int level;
    private HashMap<Integer, Node> awaitingLeft;

    public Node connectConstantSpace(Node root) {
        Node connectedParent = root; // root is the only node on depth 0 -> fully connected level (depth)
        while (true) {
            // find the leftmost node of the next (unconnected) level
            while (connectedParent != null && connectedParent.left == null && connectedParent.right == null)
                connectedParent = connectedParent.next;
            if (connectedParent == null) break; // there's no next level
            Node leftmostOfNextLevel = connectedParent.left != null ? connectedParent.left : connectedParent.right;
            // now start connecting the next level:
            for (Node cur = leftmostOfNextLevel; ; cur = cur.next) {
                if (cur == connectedParent.left && connectedParent.right != null) {
                    cur.next = connectedParent.right;
                } else {
                    do connectedParent = connectedParent.next;
                    while (connectedParent != null && connectedParent.left == null && connectedParent.right == null);
                    if (connectedParent == null) break; // next level is all connected
                    cur.next = connectedParent.left != null ? connectedParent.left : connectedParent.right;
                }
            }
            connectedParent = leftmostOfNextLevel;
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
