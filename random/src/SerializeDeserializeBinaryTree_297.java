import java.util.*;
import java.util.stream.Collectors;

public class SerializeDeserializeBinaryTree_297 {
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
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

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		Queue<Integer> levelOrderVals = new LinkedList<>();
		final String[] splits = data.substring(1, data.length() - 1).split(",");
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

	public static void main(String[] args) {
		TreeNode root = TreeNode.decodeStringToTree("[1,2,3,null,null,4,5]");
		System.out.println(TreeNode.encodeTreeToString(root));
	}
}
