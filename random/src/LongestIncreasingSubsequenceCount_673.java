public class LongestIncreasingSubsequenceCount_673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, longest = 0;
        int[] lenOfLIS = new int[n];
        int[] numOfLIS = new int[n];
        for (int startOfIS = n - 1; startOfIS >= 0; startOfIS--) {
            lenOfLIS[startOfIS] = 1; // base case; overwritten later
            int maxNextLen = 0, count = 1;
            for (int next = startOfIS + 1; next <= n - lenOfLIS[startOfIS]; next++) {// need to check all subsequent elements
                if (nums[next] > nums[startOfIS]) {
                    if (lenOfLIS[next] > maxNextLen) {
                        maxNextLen = lenOfLIS[next];
                        count = numOfLIS[next];
                    } else if (lenOfLIS[next] == maxNextLen) count += numOfLIS[next];
                }
            }
            lenOfLIS[startOfIS] = 1 + maxNextLen;
            numOfLIS[startOfIS] = count;
            longest = Math.max(longest, lenOfLIS[startOfIS]);
        }
        int count = 0;
        for (int i = 0; i < n; i++)
            if (lenOfLIS[i] == longest) count += numOfLIS[i];
        return count;
    }
}
