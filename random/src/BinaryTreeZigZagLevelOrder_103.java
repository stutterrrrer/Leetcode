import java.util.*;

public class BinaryTreeZigZagLevelOrder_103 {
    public List<List<Integer>> zigzagLevelOrderSlow(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        Queue<TreeNode> curLevel = new LinkedList<>();
        if (root != null) curLevel.add(root);
        else return ans;

        for (boolean rightToLeft = false; !curLevel.isEmpty(); rightToLeft = !rightToLeft) {
            Queue<TreeNode> nextLevel = new LinkedList<>();
            List<Integer> levelValues = new LinkedList<>();
            while (!curLevel.isEmpty()) {
                TreeNode node = curLevel.poll();
                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
                levelValues.add(node.val);
            }
            if (rightToLeft) Collections.reverse(levelValues);
            ans.add(levelValues);
            curLevel = nextLevel;
        }
        return ans;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        Stack<TreeNode> curLevel = new Stack<>();
        if (root != null) curLevel.push(root);
        else return ans;

        for (boolean curLvlRtoL = false; !curLevel.isEmpty(); curLvlRtoL = !curLvlRtoL) {
            List<Integer> levelValues = new LinkedList<>();
            Stack<TreeNode> nextLevel = new Stack<>();
            while (!curLevel.isEmpty()) {
                TreeNode node = curLevel.pop();
                levelValues.add(node.val);

                TreeNode secondPop = curLvlRtoL ? node.right : node.left;
                TreeNode firstPop = curLvlRtoL ? node.left : node.right;
                if (secondPop != null) nextLevel.push(secondPop);
                if (firstPop != null) nextLevel.push(firstPop);
            }
            ans.add(levelValues);
            curLevel = nextLevel;
        }
        return ans;
    }

    public static void main(String[] args) {
        String tree = "[1,2,3,4,5,6,7,8,9,10,11]";
        TreeNode input = TreeNode.decodeStringToTree(tree);
        BinaryTreeZigZagLevelOrder_103 solver = new BinaryTreeZigZagLevelOrder_103();
        for (List<Integer> list : solver.zigzagLevelOrder(input)) System.out.println(list);
//		solver.zigzagLevelOrder(input);
    }
}
