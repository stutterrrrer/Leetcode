public class ArithmeticSlices_413 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;
        int prevDiff = nums[1] - nums[0] - 1; // make sure it won't count
        int prevDiffCount = 0, totalSlices = 0;
        for (int cur = 1; cur <= n; cur++) {
            int curDiff = cur < n ? nums[cur] - nums[cur - 1] : prevDiff - 1; // when nums[n - 1] is end of slice
            if (curDiff != prevDiff) { // settle tab, reset diff and diff count
                for (int subSize = 3; subSize <= prevDiffCount + 1; subSize++) // total subarray size = diffCount + 1
                    totalSlices += (prevDiffCount + 1) - subSize + 1;
                prevDiff = curDiff;
                prevDiffCount = 1;
            } else prevDiffCount++;
        }
        return totalSlices;
    }
}
