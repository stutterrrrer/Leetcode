public class FindPeakElement_162 {
    public int findPeakElement(int[] nums) {
        int n = nums.length, prev = nums[0];
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num < prev) return i - 1;
            else prev = num;
        }
        return n - 1;
    }
}
