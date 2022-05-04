public class DeleteNodeInBST_450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        int curVal = root.val;
        if (key > curVal) root.right = deleteNode(root.right, key);
        else if (key < curVal) root.left = deleteNode(root.left, key);
        else // root.val == key:
            root = replace(root);
        return root;
    }

    private TreeNode replace(TreeNode root) {
        // base case - the deleted node is a leaf node.
        if (root.left == null && root.right == null) return null;

        // recursive calls: if the deleted node has at least 1 child:
        if (root.left != null) {
            root.val = findMax(root.left);
            root.left = deleteNode(root.left, root.val);
        } else { // origLeft == null && origRight != null:
            root.val = findMin(root.right);
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private int findMax(TreeNode root) {
        while (root.right != null) root = root.right;
        return root.val;
    }

    private int findMin(TreeNode root) {
        while (root.left != null) root = root.left;
        return root.val;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.decodeStringToTree("[5,3,6,2,4,null,7]");
        int key = 3;
        DeleteNodeInBST_450 solver = new DeleteNodeInBST_450();

    }
}
