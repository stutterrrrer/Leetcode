import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static String encodeTreeToString(TreeNode root) {
        Deque<Integer> valDeque = new LinkedList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            if (cur != null) {
                nodeQueue.offer(cur.left);
                nodeQueue.offer(cur.right);
                valDeque.offerLast(cur.val);
            } else valDeque.offerLast(null);
        }
        while (!valDeque.isEmpty() && valDeque.peekLast() == null) valDeque.removeLast();

        StringBuilder str = new StringBuilder("[");
        while (!valDeque.isEmpty()) str.append(valDeque.removeFirst()).append(valDeque.isEmpty() ? "" : ',');
        return str.append(']').toString();
    }

    public static TreeNode decodeStringToTree(String levelOrder) {
        Queue<Integer> levelOrderVals = new LinkedList<>();
        final String[] splits = levelOrder.substring(1, levelOrder.length() - 1).split(",");
        if (splits[0].isEmpty()) return null;
        Arrays.stream(splits)
                .map(i -> i.equals("null") ? null : Integer.parseInt(i))
                .forEach(levelOrderVals::offer);

        Queue<TreeNode> nodesAwaitingChildren = new LinkedList<>();
        TreeNode root = new TreeNode(levelOrderVals.poll());
        nodesAwaitingChildren.offer(root);

        while (!levelOrderVals.isEmpty()) {
            TreeNode parent = nodesAwaitingChildren.poll();
            Integer leftVal = levelOrderVals.poll(),
                    rightVal = levelOrderVals.isEmpty() ? null : levelOrderVals.poll();
            if (leftVal != null) {
                parent.left = new TreeNode(leftVal);
                nodesAwaitingChildren.offer(parent.left);
            }
            if (rightVal != null) {
                parent.right = new TreeNode(rightVal);
                nodesAwaitingChildren.offer(parent.right);
            }
        }
        return root;
    }
}
