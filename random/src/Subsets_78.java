import java.util.*;

public class Subsets_78 {
    private int[] nums;
    private List<List<Integer>> subsets;
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        subsets = new ArrayList<>();
        Deque<Integer> curSet = new LinkedList<>();
        includeOrExcludeNumStarting(0, curSet); // each number is either included or excluded in each set
        return subsets;
    }

    private void includeOrExcludeNumStarting(int bgn, Deque<Integer> curSet) {
        if (bgn >= nums.length) subsets.add(new ArrayList<>(curSet)); // when all numbers' in(ex)clusion are decided
        else {
            includeOrExcludeNumStarting(bgn + 1, curSet); // exclude nums[bgn]
            curSet.addLast(nums[bgn]);
            includeOrExcludeNumStarting(bgn + 1, curSet); // include nums[bgn]
            curSet.removeLast(); // remove nums[bgn] before returning to decide on in(ex)clusion of nums[bgn - 1]
        }
    }
}
