import java.util.HashMap;

public class SumKMaxSubArray_325 {
    public int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length, prefixSum = 0, maxLen = 0;
        HashMap<Integer, Integer> sumFirstIndices = new HashMap<>();
        sumFirstIndices.put(0, -1);
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            if (sumFirstIndices.containsKey(prefixSum - k))
                maxLen = Math.max(maxLen, i - sumFirstIndices.get(prefixSum - k));
            sumFirstIndices.putIfAbsent(prefixSum, i);
        }
        return maxLen;
    }
}
