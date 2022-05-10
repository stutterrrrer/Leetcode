public class FindMinimumRotatedSortedArray_153 {
    public int findMin(int[] nums) {
        int n = nums.length;
        if (nums[0] < nums[n - 1]) return nums[0]; // the array is still ascending
        int bgn = 0, end = n - 1;
        // denote nums[inflection] = min
        while (end - bgn > 1) { // terminates when bgn = inflection - 1, end = inflection
            int mid = bgn + (end - bgn) / 2;
            if (nums[mid] >= nums[bgn]) // inflection > mid
                bgn = mid;
            else // if nums[mid] < nums[bgn]: inflection <= mid
                end = mid;
        }
        return nums[end];
    }

    public static void main(String[] args) {
        int[] nums = {11, 13, 15, 17};
        FindMinimumRotatedSortedArray_153 solver = new FindMinimumRotatedSortedArray_153();
        System.out.println(solver.findMin(nums));
    }
}
