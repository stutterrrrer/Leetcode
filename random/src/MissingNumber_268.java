public class MissingNumber_268 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int countDown = (1 + n) * n / 2; // arithmetic sum from 0 to n; n <= 10^4, so sum within int range
        for (int num : nums) countDown -= num;
        return countDown;
    }
}
