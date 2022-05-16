import java.util.*;

public class Subsets_78 {
    private int[] nums;
    private int n;
    private List<List<Integer>> subsets;
    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;
        n = nums.length;
        subsets = new ArrayList<>(Collections.singletonList(new ArrayList<>())); // add an empty subset first
        Deque<Integer> curPath = new LinkedList<>();
        for (int i = 0; i < n; i++) subsetsStarting(i, curPath);
        return subsets;
    }

    private void subsetsStarting(int bgnIndex, Deque<Integer> curPath) {
        curPath.addLast(nums[bgnIndex]);
        for (int i = bgnIndex + 1; i < n; i++) subsetsStarting(i, curPath);
        subsets.add(new ArrayList<>(curPath));
        curPath.removeLast();
    }
}
