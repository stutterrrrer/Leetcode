public class MaxSumSubarray_53 {

    public int maxSubArray(int[] nums) {
        int n = nums.length, max = Integer.MIN_VALUE, i = 0, curSum = 0;
        for (; i < n && nums[i] < 0; i++) max = Math.max(max, nums[i]);
        if (i == n) return max;
        while (i < n) {
            for (; i < n && nums[i] >= 0; i++) curSum += nums[i];
            max = Math.max(max, curSum);
            for (; i < n && nums[i] < 0; i++) curSum += nums[i];
            curSum = Math.max(curSum, 0);
        }
        return max;
    }

    public int maxSubArrayMessy(int[] nums) {
        int n = nums.length, max = Integer.MIN_VALUE, prevNonNegSum = 0, i = 0;
        for (; i < n && nums[i] < 0; i++) max = Math.max(max, nums[i]);
        if (i == n) return max; // in case array has only negative elements
        for (; i < n && nums[i] >= 0; i++) prevNonNegSum += nums[i];
        max = prevNonNegSum;
        while (i < n) {
            int negGap = 0, curNonNegSum = 0;
            for (; i < n && nums[i] < 0; i++) negGap += nums[i];
            for (; i < n && nums[i] >= 0; i++) curNonNegSum += nums[i];
            if (prevNonNegSum + negGap > 0) // connect the 2 non-negative sums
                prevNonNegSum += negGap + curNonNegSum;
            else prevNonNegSum = curNonNegSum;
            max = Math.max(max, prevNonNegSum);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        MaxSumSubarray_53 solver = new MaxSumSubarray_53();
        System.out.println(solver.maxSubArray(nums));
    }
}
