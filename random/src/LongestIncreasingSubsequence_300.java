public class LongestIncreasingSubsequence_300 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length, max = 0;
        int[] memo = new int[n];
        for (int startOfIS = n - 1; startOfIS >= 0; startOfIS--) {
            memo[startOfIS] = 1; // base case; overwritten later
            for (int next = startOfIS + 1; next < n; next++) // need to check all subsequent elements
                if (nums[next] > nums[startOfIS])
                    memo[startOfIS] = Math.max(memo[startOfIS], memo[next] + 1); // to find the longest IS we can add nums[startOfIs] to the beginning of
            max = Math.max(max, memo[startOfIS]);
        }
        return max;
    }

    public int lengthOfLISBackTrack(int[] nums) {
        int n = nums.length;
        int[] memoization = new int[n]; // memo[i] = longest strictly increasing subsequence starting nums[i]
        int max = 0;
        for (int i = 0; i < n; i++)
            max = Math.max(max, LISstarting(i, -1, memoization, nums)); // -1 as prev index
        return max;
    }

    private int LISstarting(int curIndex, int prevIndex, int[] memoization, int[] nums) {
        if (prevIndex >= 0 && nums[curIndex] <= nums[prevIndex]) return 0; // if this is first, no prev(-1) to compare
        if (memoization[curIndex] == 0) { // if we haven't visited this index
            int longestStartingNext = 0;
            for (int next = curIndex + 1; next < nums.length; next++)
                longestStartingNext = Math.max(longestStartingNext, LISstarting(next, curIndex, memoization, nums));
            memoization[curIndex] = 1 + longestStartingNext; // every time we visit an index, remember it
        }
        return memoization[curIndex];
    }
}
