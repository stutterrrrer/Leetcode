public class BSTinOrderSuccessor_285 {
    public TreeNode inorderSuccessorIntuitive(TreeNode root, TreeNode p) {
        if (p.right != null) { // then the successor must be p's descendant
            TreeNode successor = p.right;
            while (successor.left != null) successor = successor.left;
            return successor;
        } // else successor must be p's ancestor
        TreeNode cur = root, successor = null;
        while (cur != p) {
            if (p.val < cur.val) {
                if (successor == null || cur.val < successor.val) successor = cur;
                cur = cur.left;
            } else cur = cur.right;
        }
        return successor;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode ceiling = null;
        while (root != null) {
            if (root.val > p.val) {
                ceiling = root; // each iteration will have root closer to p's value; so always update successor
                root = root.left; // will reach null if p has right child
            } else { // root.val <= p.val
                root = root.right; // will reach null if p has no right child
            }
        }
        return ceiling;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.decodeStringToTree("[5,3,6,1,4,null,null,null,2]");
    }
}
