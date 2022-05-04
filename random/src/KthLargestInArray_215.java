import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestInArray_215 {

    public int findKthLargest(int[] nums, int k) {
        // min priority queue of capacity k - keep k largest elements of the array in it
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(Comparator.comparingInt(i -> i));
        for (int n : nums) {
            minPQ.add(n);
            if (minPQ.size() > k)
                minPQ.poll();
        }
        return minPQ.poll();
    }

    public int findKthLargest1(int[] nums, int k) {
        int n = nums.length;
        // decide whether to store k largest elements, or (n - k + 1) smallest elements of the array in PQ
        int capacity = Math.min(k, n - k + 1);
        boolean isMinPQ = k < n - k + 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(capacity, Comparator.comparingInt(isMinPQ ?
                i -> i : i -> -i)); // i -> -i : max pq.
        for (int i = 0; i < capacity; i++) pq.add(nums[i]);
        for (int i = capacity; i < n; i++) {
            int num = nums[i], head = pq.peek();
            if ((isMinPQ && num > head) || (!isMinPQ && num < head)) {
                pq.remove();
                pq.add(num);
            }
        }
        return pq.remove();
    }

    public int findKthLargest2(int[] nums, int k) {
        int n = nums.length;
        // decide whether to store k largest elements, or (n - k + 1) smallest elements of the array in PQ
        int capacity = Math.min(k, n - k + 1);
        boolean isMinPQ = k < n - k + 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>(capacity, Comparator.comparingInt(isMinPQ ?
                i -> i : i -> -i)); // i -> -i : max pq.
        for (int i = 0; i < capacity; i++) pq.add(nums[i]);
        if (isMinPQ) {
            for (int i = capacity; i < n; i++) {
                int num = nums[i];
                if (num > pq.peek()) {
                    pq.remove();
                    pq.add(num);
                }
            }
        } else {
            for (int i = capacity; i < n; i++) {
                int num = nums[i];
                if (num < pq.peek()) {
                    pq.remove();
                    pq.add(num);
                }
            }
        }
        return pq.remove();
    }
}
