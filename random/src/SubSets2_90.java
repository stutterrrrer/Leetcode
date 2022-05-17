import java.util.*;

public class SubSets2_90 {
    private int[] distinct;
    private HashMap<Integer, Integer> frequencies;
    private List<List<Integer>> subsets;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        frequencies = new HashMap<>();
        for (int num : nums) frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        distinct = frequencies.keySet().stream().mapToInt(i -> i).toArray();
        subsets = new ArrayList<>();
        // each distinct element i has [0, frequencies.get(i)] options
        Deque<Integer> curSet = new LinkedList<>();
        decideFreqOfNumberStarting(0, curSet);
        return subsets;
    }

    private void decideFreqOfNumberStarting(int bgn, Deque<Integer> curSet) {
        if (bgn >= distinct.length) subsets.add(new ArrayList<>(curSet)); // all distinct number frequencies decided
        else { // decide on this distinct number's frequency in the set: [0, maxFreq]
            int distinctNum = distinct[bgn], maxFreq = frequencies.get(distinctNum);
            decideFreqOfNumberStarting(bgn + 1, curSet); // this distinct number's frequency = 0
            for (int i = 0; i < maxFreq; i++) {
                curSet.addLast(distinctNum); // this distinct number's frequency = [1, maxFreq]
                decideFreqOfNumberStarting(bgn + 1, curSet);
            }
            // delete all occurrences of distinct[bgn] before returning to distinct[bgn - 1]'s another frequency
            for (int i = 0; i < maxFreq; i++) curSet.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 3};
        SubSets2_90 solver = new SubSets2_90();
        for (List<Integer> list : solver.subsetsWithDup(nums)) System.out.println(list);
    }
}
