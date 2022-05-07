public class MoveZerosToArrayEnd_283 {
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        for (int i = 0, nextNonZero = 0; i < n; i++) {
            int num = nums[i];
            if (num != 0) {
                if (i > nextNonZero) { // exchange 0 and non-zero
                    nums[nextNonZero] = num;
                    nums[i] = 0;
                }
                nextNonZero++;
            }
        }
    }
}
