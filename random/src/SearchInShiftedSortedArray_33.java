public class SearchInShiftedSortedArray_33 {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        int bgn = 0, end = n - 1;
        while (bgn <= end) {
            int mid = bgn + (end - bgn) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] >= nums[bgn]) { // mid < pivot; left half is sorted (includes whole sub-array is sorted)
                if (target >= nums[bgn] && target < nums[mid]) end = mid - 1; // within sorted left half -> go to left
                else bgn = mid + 1; // not within sorted left half -> go to the other half (sorted or not)
            } else { // mid >= pivot; only right half is sorted
                if (target > nums[mid] && target <= nums[end]) bgn = mid + 1; // within sorted right half -> go right
                else end = mid - 1; // go to the other half.
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1};
        int target = 1;
        SearchInShiftedSortedArray_33 solver = new SearchInShiftedSortedArray_33();
        System.out.println(solver.search(nums, target));
    }
}
