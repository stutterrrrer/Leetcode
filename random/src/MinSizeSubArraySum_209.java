public class MinSizeSubArraySum_209 {
    public int minSubArrayLen(int target, int[] nums) {
        // min length of (positive integer only sub-array) that sums up to >= target.
        int n = nums.length, sum = 0, minLen = n + 1;
        for (int bgn = 0, end = 0; ; ) { // at start of each iteration, sum of nums[bgn, end - 1] < target
            while (end < n && sum < target) sum += nums[end++]; // sum of nums[bgn, end - 1] >= target
            if (sum < target) break; // if end is already == n, and sum still < target.
            while (sum >= target) sum -= nums[bgn++]; // sum of nums[bgn, end - 1] < target.
            // shortest sub-array that sum to >= target is [bgn - 1, end - 1]; if length = 1: bgn = end, sum = 0.
            minLen = Math.min(minLen, end - bgn + 1);
        }
        return minLen == n + 1 ? 0 : minLen;
    }

    public static void main(String[] args) {
        int[] input = {2, 3, 1, 2, 4, 3};
        int target = 6;
        System.out.println(new MinSizeSubArraySum_209().minSubArrayLen(target, input));
    }
}
