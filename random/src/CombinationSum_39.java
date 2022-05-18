import java.util.*;

public class CombinationSum_39 {
    private int[] candidates;
    private int remain;
    private List<List<Integer>> combinationsSummingToTarget;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); // limit search efforts: when (remain - cand[i] < 0), can ignore all cand[j] (j > i)
        this.candidates = candidates;
        this.remain = target;
        combinationsSummingToTarget = new ArrayList<>();
        Deque<Integer> curComb = new ArrayDeque<>(); // try LinkedList and ArrayDeque
        decideFreqOfNumStarting(0, curComb);
        return combinationsSummingToTarget;
    }

    private void decideFreqOfNumStarting(int bgn, Deque<Integer> curComb) {
        if (remain == 0) combinationsSummingToTarget.add(new ArrayList<>(curComb));
        else if (bgn < candidates.length) { // remain > 0; won't be < 0 bc of loop condition in prev DFONS(bgn-1) call
            int num = candidates[bgn], count = 0;
            decideFreqOfNumStarting(bgn + 1, curComb); // this num included 0 times
            for (; remain - num >= 0; count++) { // this num included (target / num) times
                curComb.addLast(num);
                remain -= num;
                decideFreqOfNumStarting(bgn + 1, curComb);
            } // no need to try nums[bgn + 1] bc array is sorted; backtrack to another frequency of nums[bgn - 1]
            for (int i = 0; i < count; i++) {
                remain += num;
                curComb.removeLast();
            }
        }
    }
}
