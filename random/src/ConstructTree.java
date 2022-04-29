import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ConstructTree {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int TREE_COUNT = 2;  // how many arrays of nodes does the program expect?

		for (int i = 0; i < TREE_COUNT; i++) {
			String tree = in.nextLine().trim();
			TreeNode root = constructTree(tree);
			// what to do with each constructed tree:
		}
	}


	public static TreeNode constructTree(String tree) {
		tree = tree.substring(1, tree.length() - 1);
		String[] nodes = tree.split(",");
		TreeNode root = null;
		if (nodes[0].equals("")) return root;
		root = new TreeNode(Integer.parseInt(nodes[0]));
		int index = 1;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (index < nodes.length) {
			TreeNode tmp = queue.poll();
			if (!nodes[index].equals("null")) {
				TreeNode leftNode = new TreeNode(Integer.parseInt(nodes[index]));
				tmp.left = leftNode;
				queue.offer(leftNode);
			}
			index++;
			if (index >= nodes.length)
				break;
			if (!nodes[index].equals("null")) {
				TreeNode rightNode = new TreeNode(Integer.parseInt(nodes[index]));
				tmp.right = rightNode;
				queue.offer(rightNode);
			}
			index++;
		}
		return root;
	}
}
