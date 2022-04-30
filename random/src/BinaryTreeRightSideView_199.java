import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView_199 {
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> rightSideView = new LinkedList<>();
        findRightSideView(rightSideView, root, 0);
        return rightSideView;
    }

    private void findRightSideView(List<Integer> rightSideView, TreeNode node, int curDepth) {
        if (node == null) return;
        if (curDepth >= rightSideView.size()) rightSideView.add(node.val);
        findRightSideView(rightSideView, node.right, curDepth + 1);
        findRightSideView(rightSideView, node.left, curDepth + 1);
    }

    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> rightSideView = new LinkedList<>();
        Queue<TreeNode> curDepth = new LinkedList<>();
        if (root != null) curDepth.add(root);
        else return rightSideView;
        while (!curDepth.isEmpty()) {
            Queue<TreeNode> nextDepth = new LinkedList<>();
            TreeNode node = curDepth.poll();
            rightSideView.add(node.val);
            addChildrenToNextDepth(nextDepth, node);
            while (!curDepth.isEmpty()) {
                node = curDepth.poll();
                addChildrenToNextDepth(nextDepth, node);
            }
            curDepth = nextDepth;
        }
        return rightSideView;
    }

    private void addChildrenToNextDepth(Queue<TreeNode> nextDepth, TreeNode node) {
        if (node.right != null) nextDepth.offer(node.right);
        if (node.left != null) nextDepth.offer(node.left);
    }

    public static void main(String[] args) {
        TreeNode input = ConstructTree.constructTree("[1,2,3,4,null,5,6,7,null]");
        BinaryTreeRightSideView_199 solver = new BinaryTreeRightSideView_199();
        System.out.println(solver.rightSideViewDFS(input));
        System.out.println(solver.rightSideViewBFS(input));
    }
}
