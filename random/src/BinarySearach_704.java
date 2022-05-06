public class BinarySearach_704 {
    public int search(int[] nums, int target) {
        // return the target index or -1
        for (int bgn = 0, end = nums.length - 1; bgn <= end; ) {
            int mid = (bgn + end) / 2, midNum = nums[mid];
            if (target > midNum) bgn = mid + 1;
            else if (target < midNum) end = mid - 1;
            else return mid;
        }
        return -1;
    }
}
