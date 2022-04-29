import java.util.HashMap;

public class ConstructTreePreOrderInOrder_105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        HashMap<Integer, Integer> mapIO = new HashMap<>();
        for (int i = 0; i < n; i++) mapIO.put(inorder[i], i);
        return buildTree(preorder, mapIO, 0, n - 1, 0);
    }

    private TreeNode buildTree(int[] preorder, HashMap<Integer, Integer> mapIO,
                               int bgnIO, int endIO, int rootInPO) {
        if (bgnIO > endIO) return null;
        // the tree, where preorder[rootInPO] is root, has size (endIO - bgnIO + 1);
        int rootVal = preorder[rootInPO];
        int rootInIO = mapIO.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preorder, mapIO, bgnIO, rootInIO - 1, rootInPO + 1);
        int leftTreeSize = rootInIO - bgnIO;
        root.right = buildTree(preorder, mapIO, rootInIO + 1, endIO, rootInPO + leftTreeSize + 1);

        return root;
    }
}

class ConstructTreeSolverInstanceVariables {
    private int[] preorder;
    private HashMap<Integer, Integer> mapIO;
    private int rootIndexInPO;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        int n = inorder.length;
        mapIO = new HashMap<>();
        for (int i = 0; i < n; i++) mapIO.put(inorder[i], i);
        rootIndexInPO = 0;

        return buildTree(0, n - 1);
    }

    private TreeNode buildTree(int bgnIO, int endIO) {
        if (bgnIO > endIO) return null;
        int rootVal = preorder[rootIndexInPO++]; // increment rootIndexInPO for every node.
        TreeNode root = new TreeNode(rootVal);

        int rootIndexInIO = mapIO.get(rootVal);
        root.left = buildTree(bgnIO, rootIndexInIO - 1);
        root.right = buildTree(rootIndexInIO + 1, endIO);
        return root;
    }
}
