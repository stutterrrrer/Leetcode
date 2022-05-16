import java.util.*;

public class SubSets2_90 {
    private int[] distinct;
    private int d;
    private HashMap<Integer, Integer> frequencies;
    private List<List<Integer>> subsets;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        frequencies = new HashMap<>();
        for (int num : nums) frequencies.put(num, frequencies.getOrDefault(num, 0) + 1);
        distinct = frequencies.keySet().stream().mapToInt(i -> i).toArray();
        d = distinct.length;
        subsets = new ArrayList<>(Collections.singleton(new ArrayList<>())); // first include an empty set.
        Deque<Integer> presentDistinct = new LinkedList<>();
        HashMap<Integer, Integer> curSetFreq = new HashMap<>();
        for (int i = 0; i < d; i++) setsStarting(i, presentDistinct, curSetFreq);
        return subsets;
    }

    private void setsStarting(int bgn, Deque<Integer> presentDistinct, HashMap<Integer, Integer> curSetFreq) {
        presentDistinct.addLast(distinct[bgn]);
        populateSets(presentDistinct, curSetFreq); // before including any later distinct numbers
        curSetFreq.clear(); // each set of distinct numbers gets its own set frequencies map
        for (int i = bgn + 1; i < d; i++) setsStarting(i, presentDistinct, curSetFreq); // include more distinct numbers
        presentDistinct.removeLast(); // remove this distinct number
    }

    private void populateSets(Deque<Integer> presentDistinct, HashMap<Integer, Integer> curSetFreq) {
        if (presentDistinct.isEmpty()) { // all distinct number's frequencies having been determined:
            List<Integer> curSet = new ArrayList<>();
            for (int num : curSetFreq.keySet())
                for (int i = 0; i < curSetFreq.get(num); i++)
                    curSet.add(num);
            subsets.add(curSet);
        } else {
            int num = presentDistinct.removeLast(), frequency = frequencies.get(num);
            for (int i = 1; i <= frequency; i++) {
                curSetFreq.put(num, i); // determine this distinct number's frequency in this set
                populateSets(presentDistinct, curSetFreq); // determine the next distinct number's frequency.
            }
            presentDistinct.addLast(num); // go back to the previous distinct number, to pick another frequency
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 3};
        SubSets2_90 solver = new SubSets2_90();
        for (List<Integer> list : solver.subsetsWithDup(nums)) System.out.println(list);
    }
}
