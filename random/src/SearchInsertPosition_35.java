public class SearchInsertPosition_35 {
    public int searchInsert(int[] nums, int target) {
        int bgn = 0, end = nums.length - 1;
        while (bgn <= end) {
            int mid = bgn + (end - bgn) / 2;
            int midNum = nums[mid];
            if (target > midNum) bgn = mid + 1;
            else if (target < midNum) end = mid - 1;
            else return mid;
        }
        // terminate when : bgn = end + 1 && nums[bgn] > target > nums[end] (if bgn != n, end != -1)
        return bgn;
    }
}
