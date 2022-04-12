import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class SubArraySumEqualsK_560 {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        // can use hash map <curSum, List of indices>
        // to store the indices where curSum appears, and return the specific sub-array instead of just the count.
        HashMap<Integer, Integer> cumulativeSums = new HashMap<>();
        cumulativeSums.put(0, 1);

        for (int curSum = 0, i = 0; i < nums.length; i++) {
            curSum += nums[i];
            count += cumulativeSums.getOrDefault(curSum - k, 0);
            cumulativeSums.put(curSum, cumulativeSums.getOrDefault(curSum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr1 = {1};
        int k1 = 0;
        int[] arr2 = {1, -1, 0};
        int k2 = 0; // answer: 3
        int[] arr3 = {5, -2, 3, 2, -7, 0, 2};
        int k3 = 3; // answer: 4

        SubArraySumEqualsK_560 solver = new SubArraySumEqualsK_560();
        System.out.println(solver.subarraySum(arr3, k3));
    }
}
