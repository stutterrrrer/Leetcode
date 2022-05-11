public class SubArrayProductLessThanK_713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int count = 0, product = 1;
        for (int end = 0, bgn = end; end < n; end++) {
            if (nums[end] < k) { // end will be included in the subarray
                product *= nums[end];
                while (product >= k) product /= nums[bgn++];
            } else { // find next single element < k
                while (end < n && nums[end] >= k) end++;
                if (end == n) break;
                bgn = end; // 1 element
                product = nums[end];
            }
            count += end - bgn + 1;
        }
        return count;
    }

    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (k <= 1) return 0;
        int n = nums.length;
        int count = 0, product = 1;
        for (int right = 0, left = 0; right < n; right++) {
            product *= nums[right];
            while (product >= k) product /= nums[left++]; // if nums[right] >= k, then (left = right + 1) after loop
            // new right boundary: adds (right - left + 1) new sub-arrays: 1 of each size between [1, right - left - 1];
            count += right - left + 1; // if nums[right] >= k, then count += 0;
        }
        return count;
    }
}

