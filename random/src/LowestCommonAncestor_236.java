import org.w3c.dom.Node;

import java.util.Stack;

public class LowestCommonAncestor_236 {

	private static class NodeStatus {
		TreeNode node;
		int visitedSubtrees = 0;
		int descendants = 0;

		public NodeStatus(TreeNode node) {
			this.node = node;
		}
	}

	public TreeNode lowestCommonAncestorIntuitive(TreeNode root, TreeNode p, TreeNode q) {
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

	private static class NodeStatus2 {
		public TreeNode node;
		public int visitedSubTrees;

		public NodeStatus2(TreeNode node, int visitedSubTrees) {
			this.node = node;
			this.visitedSubTrees = visitedSubTrees;
		}
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		int found = 0, pVal = p.val, qVal = q.val;
		TreeNode lowestAncestorToFirst = root; // 0-indexed, SILAFF
		Stack<NodeStatus2> stack = new Stack<>(); // Integer: #of visited subtrees of this node
		NodeStatus2 cur = new NodeStatus2(root, 0);
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
					cur = new NodeStatus2(++cur.visitedSubTrees == 1 ? cur.node.left : cur.node.right, 0);
					continue;
				}
				// if both of the subtrees of curNode has been visited:
				if (found == 1 && cur.node == lowestAncestorToFirst)
					lowestAncestorToFirst = stack.peek().node;
				cur = stack.pop();
			}
		}
	}

	public TreeNode testHelper(TreeNode root, int pVal, int qVal) {
		TreeNode p = new TreeNode(pVal);
		TreeNode q = new TreeNode(qVal);
		return lowestCommonAncestor(root, p, q);
	}

	public static void main(String[] args) {
		TreeNode root = ConstructTree.constructTree("[3,5,1,6,2,0,8,null,null,7,4]");
		int pVal = 5, qVal = 1;
		LowestCommonAncestor_236 solver = new LowestCommonAncestor_236();
		System.out.println(solver.testHelper(root, pVal, qVal).val);
	}
}
