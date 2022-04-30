import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PathSum2_113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> targetSumPaths = new LinkedList<>();
        Stack<Integer> curPath = new Stack<>();
        findSum(root, curPath, targetSum, targetSumPaths);
        return targetSumPaths;
    }

    private void findSum(TreeNode node, Stack<Integer> curPath, int remainingSum,
                         List<List<Integer>> targetSumPaths) {
        if (node == null) return;
		remainingSum -= node.val;
        curPath.push(node.val);

        findSum(node.left, curPath, remainingSum, targetSumPaths);
        findSum(node.right, curPath, remainingSum, targetSumPaths);

        if (node.left == null && node.right == null && remainingSum == 0)
            targetSumPaths.add(new LinkedList<>(curPath));
        // before returning to parent: remove this node from curPath
        curPath.pop();
    }

    public static void main(String[] args) {
        TreeNode root = ConstructTree.constructTree("[5,4,8,11,null,13,4,7,2,null,null,5,1]");
        int target = 22;
        PathSum2_113 solver = new PathSum2_113();
        for (List<Integer> path : solver.pathSum(root, target))
            System.out.println(path);
    }
}
