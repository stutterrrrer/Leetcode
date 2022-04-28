public class SortedArrToBinSearchTree_108 {
	public TreeNode sortedArrayToBST(int[] nums) {
		return subArrayToTree(nums, 0, nums.length - 1);
	}

	private TreeNode subArrayToTree(int[] arr, int bgn, int end) {
		if (bgn > end) return null;
		int mid = (bgn + end ) / 2;
		TreeNode root = new TreeNode(arr[mid]);
		root.left = subArrayToTree(arr, bgn, mid - 1);
		root.right = subArrayToTree(arr, mid + 1, end);
		return root;
	}
}
