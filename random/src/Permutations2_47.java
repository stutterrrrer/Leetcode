import java.util.*;

public class Permutations2_47 {
    private List<List<Integer>> permutations;
    private int n;
    private int[] nums;
    private boolean[] unavailable;
    private HashMap<Integer, Integer> uniqueNums;

    public List<List<Integer>> permuteUniqueSortArray(int[] nums) {
        permutations = new ArrayList<>();
        n = nums.length;
        this.nums = nums;
        unavailable = new boolean[n];
        Arrays.sort(nums);
        Deque<Integer> curPermu = new LinkedList<>();
        findPermuSortArray(curPermu);
        return permutations;
    }

    private void findPermuSortArray(Deque<Integer> curPermu) {
        if (curPermu.size() == n) permutations.add(new ArrayList<>(curPermu));
        else {
            for (int i = 0; i < n; i++) {
                if (unavailable[i]) continue;
                while (i < n - 1 && nums[i + 1] == nums[i] && !unavailable[i + 1]) i++; // find the last available occurrence of this unique number
                unavailable[i] = true; // so in this iteration we won't meet another available nums[i] (avoid duplicate)
                curPermu.addLast(nums[i]);
                findPermuSortArray(curPermu);
                curPermu.removeLast();
                unavailable[i] = false; // free up for next slot of permutation, or returning to iterate over the previous slot
            }
        }
    }

    public List<List<Integer>> permuteUniqueHashMap(int[] nums) {
        n = nums.length;
        uniqueNums = new HashMap<>();
        permutations = new ArrayList<>();
        for (int num : nums) uniqueNums.put(num, uniqueNums.getOrDefault(num, 0) + 1);
        Deque<Integer> curPermu = new LinkedList<>();
        findPermuHashMap(0, curPermu);
        return permutations;
    }

    private void findPermuHashMap(int count, Deque<Integer> curPermu) {
        if (count == n) permutations.add(new ArrayList<>(curPermu));
        else {
            for (int num : uniqueNums.keySet()) {
                if (uniqueNums.get(num) == 0) continue; // unavailable
                curPermu.addLast(num);
                uniqueNums.put(num, uniqueNums.get(num) - 1);
                findPermuHashMap(count + 1, curPermu);
                uniqueNums.put(num, uniqueNums.get(num) + 1);
                curPermu.removeLast();
            }
        }
    }
}
