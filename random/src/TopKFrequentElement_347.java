import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequentElement_347 {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);
        final int omitted = freq.size() - k;
        if (k < omitted) {
            // min PQ that stores the k most frequent elements
            PriorityQueue<Integer> topKFrequent = new PriorityQueue<>(k, Comparator.comparingInt(freq::get));
            for (int distinctNum : freq.keySet()) {
                if (topKFrequent.size() < k) topKFrequent.add(distinctNum);
                else if (freq.get(distinctNum) > freq.get(topKFrequent.peek())) {
                    topKFrequent.remove();
                    topKFrequent.add(distinctNum);
                }
            }
            return topKFrequent.stream().mapToInt(i -> i).toArray();
        } else {
            if (omitted != 0) {
                // max PQ that stores the (#distinct - k) least frequent elements
                PriorityQueue<Integer> rarest = new PriorityQueue<>(omitted, Comparator.comparingInt(i -> -freq.get(i)));
                for (int distinctNum : freq.keySet()) {
                    if (rarest.size() < omitted) rarest.add(distinctNum);
                    else if (freq.get(distinctNum) < freq.get(rarest.peek())) {
                        rarest.remove();
                        rarest.add(distinctNum);
                    }
                }
                for (int leastFrequent : rarest) freq.remove(leastFrequent);
            }
            return freq.keySet().stream().mapToInt(i -> i).toArray();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        int k = 1;
        TopKFrequentElement_347 solver = new TopKFrequentElement_347();
        for (int i : solver.topKFrequent(nums, k)) System.out.print(i + ", ");
    }
}
