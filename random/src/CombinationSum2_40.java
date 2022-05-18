import java.util.*;

public class CombinationSum2_40 {
    private int[] nums; // each unique number included [0, maxFreq] times.
    private int remain;
    private List<List<Integer>> combinations;
    private Map<Integer, Integer> freq;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        nums = candidates;
        remain = target;
        combinations = new ArrayList<>();
        Deque<Integer> curComb = new ArrayDeque<>();
        decideFreqOfNumStarting(0, curComb);
        return combinations;
    }

    private void decideFreqOfNumStarting(int bgn, Deque<Integer> curComb) {
        if (remain == 0) combinations.add(new ArrayList<>(curComb));
        else if (bgn < nums.length) {
            int nextDist = bgn + 1, curNum = nums[bgn], repeat = 0;
            while (nextDist < nums.length && nums[nextDist] == curNum) nextDist++; // find next distinct number
            decideFreqOfNumStarting(nextDist, curComb); // curNum is included 0 times
            for (; remain - curNum >= 0 && bgn + repeat < nextDist; repeat++) {
                curComb.addLast(curNum); // curNum included [1, repeat] times
                remain -= curNum;
                decideFreqOfNumStarting(nextDist, curComb);
            } // because nums is sorted, if (remain - curNum < 0), we know nums[i >= nextDist] won't be included:
            for (int i = 0; i < repeat; i++) { // so backtrack and return to another freq of previous distinct number
                curComb.removeLast();
                remain += curNum;
            }
        }
    }

    public List<List<Integer>> combinationSum2Map(int[] candidates, int target) {
        freq = new HashMap<>();
        for (int num : candidates) freq.put(num, freq.getOrDefault(num, 0) + 1);
        nums = freq.keySet().stream().mapToInt(i -> i).sorted().toArray(); // sort to limit search efforts
        remain = target;
        combinations = new ArrayList<>();
        Deque<Integer> curComb = new ArrayDeque<>();
        decideFreqOfNumStartingMap(0, curComb);
        return combinations;
    }

    private void decideFreqOfNumStartingMap(int bgn, Deque<Integer> curComb) {
        if (remain == 0) combinations.add(new ArrayList<>(curComb));
        else if (bgn < nums.length) { // remain > 0; and if bgn == n, return to bgn - 1
            decideFreqOfNumStartingMap(bgn + 1, curComb); // include this number 0 times
            int num = nums[bgn], count = 0, maxFreq = freq.get(num);
            for (; remain - num >= 0 && count < maxFreq; count++) {
                curComb.addLast(num); // include num [1, count] times
                remain -= num;
                decideFreqOfNumStartingMap(bgn + 1, curComb);
            } // bc dist[] is sorted, if (remain - dist[i] < 0), all dist[j > i] won't be included. return to i - 1
            for (int i = 0; i < count; i++) { // backtrack before returning to DFONS(bgn - 1);
                curComb.removeLast();
                remain += num;
            }
        }
    }
}
