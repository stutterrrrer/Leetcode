import java.util.Arrays;

public class HouseRobber_198 {

    int robVerbose(int[] nums) {
        int n = nums.length;
        if (n < 3) return Arrays.stream(nums).max().orElse(0);
        int[] maxStartingBgnPlus = new int[3];
        maxStartingBgnPlus[2] = Math.max(nums[n - 2], nums[n - 1]); // max in range [n - 2, n - 1]
        maxStartingBgnPlus[1] = Math.max(nums[n - 3] + nums[n - 1], nums[n - 2]); // max in range [n - 3, n - 1];
        if (n == 3) return maxStartingBgnPlus[1]; // max in range [0, 2];
        for (int bgn = n - 4; bgn >= 0; bgn--) {
            maxStartingBgnPlus[0] = Math.max(
                    nums[bgn] + maxStartingBgnPlus[2],
                    maxStartingBgnPlus[1]);
            maxStartingBgnPlus[2] = maxStartingBgnPlus[1]; // update -> for bgn--
            maxStartingBgnPlus[1] = maxStartingBgnPlus[0];
        }
        return maxStartingBgnPlus[0];
    }

    int rob(int[] nums) {
        int n = nums.length; // constrain: n >= 1
        int[] maxStartingBgnPlus = new int[3];
        for (int bgn = n - 1; bgn >= 0; bgn--) { // when bgn = n - 1:
            maxStartingBgnPlus[0] = Math.max( // maxStarting[n - 1] =
                    nums[bgn] + maxStartingBgnPlus[2], // max( nums[n - 1] + 0,
                    maxStartingBgnPlus[1]); // 0)
            maxStartingBgnPlus[2] = maxStartingBgnPlus[1]; // update -> for bgn--
            maxStartingBgnPlus[1] = maxStartingBgnPlus[0];
        }
        return maxStartingBgnPlus[0];
    }

    int robCircular(int[] nums) {
        int n = nums.length;
        int max = nums[0]; // in case n == 1
        for (int last = n - 2; last >= 0 && last <= n - 1; last++) { // 2 dynamic programming process;
            int[] maxStartingBgnPlus = new int[3];
            for (int bgn = last; bgn >= last - (n - 2); bgn--) { // rob houses[0, n - 2] or houses[1, n - 1]
                maxStartingBgnPlus[0] = Math.max(
                        nums[bgn] + maxStartingBgnPlus[2],
                        maxStartingBgnPlus[1]);
                maxStartingBgnPlus[2] = maxStartingBgnPlus[1];
                maxStartingBgnPlus[1] = maxStartingBgnPlus[0];
            }
            max = Math.max(max, maxStartingBgnPlus[0]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        HouseRobber_198 solver = new HouseRobber_198();
        System.out.println(solver.robCircular(nums));
    }
}