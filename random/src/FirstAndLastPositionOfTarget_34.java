public class FirstAndLastPositionOfTarget_34 {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        if (n == 0 || nums[0] > target || nums[n - 1] < target) return new int[]{-1, -1};

        int bgn = 0, end = n - 1;
        if (nums[0] == target || nums[n - 1] == target) {
            bgn = nums[0] == target ? 0 : findExtremity(n - 1, 0, nums, target);
            end = nums[n - 1] == target ? n - 1 : findExtremity(0, n - 1, nums, target);
        } else { //if (nums[bgn] < target && nums[end] > target)
            // shrink the [bgn, end] range by half while maintaining nums[bgn] < target && nums[end] > target
            while (true) {
                if (end - bgn == 1) return new int[]{-1, -1}; // target doesn't exist
                int mid = bgn + (end - bgn) / 2;
                if (nums[mid] < target) bgn = mid;
                else if (nums[mid] > target) end = mid;
                else break; // found target at nums[mid]
            }
            int mid = bgn + (end - bgn) / 2;
            bgn = findExtremity(mid, bgn, nums, target);
            end = findExtremity(mid, end, nums, target);
        }
        return new int[]{bgn, end};
    }

    private int findExtremity(int equalIndex, int unequalIndex, int[] nums, int target) {
        // shrink the range by half while maintaining nums[equalIndex] == target && nums[unequalIndex] != target.
        while (Math.abs(equalIndex - unequalIndex) > 1) {
            int mid = equalIndex + (unequalIndex - equalIndex) / 2;
            if (nums[mid] == target) equalIndex = mid;
            else unequalIndex = mid;
        }
        return equalIndex;
    }

    public static void main(String[] args) {
        int[] input = {6,6,6,6};
        int target = 6;
        FirstAndLastPositionOfTarget_34 solver = new FirstAndLastPositionOfTarget_34();
        for (int i : solver.searchRange(input, target)) System.out.print(i + ", ");
    }
}
