public class SearchInShiftedSortedArray_33 {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) return -1;
        int bgn = 0, end = n - 1;
        while (bgn <= end) {
            int mid = bgn + (end - bgn) / 2, midNum = nums[mid];
            if (midNum == target) return mid;
            if (nums[bgn] <= nums[end]) { // base case - normal binary search within ascending sub-array
                if (target > midNum) bgn = mid + 1;
                else end = mid - 1;
            } else { // recursive: bgn < pivot && end >= pivot - where nums[pivot] == minimum of the array
                if (midNum >= nums[bgn]) { // mid < pivot
                    if (target >= nums[bgn] && target < midNum) end = mid - 1; // into ascending sub-array base case
                    else bgn = mid + 1; // recur; potentially goes into ascending sub-array
                } else { // mid >= pivot
                    if (target < nums[bgn] && target > midNum) bgn = mid + 1; // into ascending sub-array base case
                    else end = mid - 1; // recur; potentially goes into ascending sub-array
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        SearchInShiftedSortedArray_33 solver = new SearchInShiftedSortedArray_33();
        System.out.println(solver.search(nums, target));
    }
}
