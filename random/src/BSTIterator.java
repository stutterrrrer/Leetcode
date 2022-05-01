import java.util.LinkedList;
import java.util.Queue;

public class BSTIterator {
	private Queue<Integer> queue;

	public BSTIterator(TreeNode root) {
		queue = new LinkedList<>();
		inOrder(root);
	}

	private void inOrder(TreeNode root) {
		if (root == null) return;
		inOrder(root.left);
		queue.offer(root.val);
		inOrder(root.right);
	}

	public int next() {
		return hasNext() ? queue.poll() : 0;
	}

	public boolean hasNext() {
		return queue.size() > 0;
	}
}
