import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class a_parth1 {

    public int findMin(int[] arr) {
        int min = 0;
        for (int i : arr)
            if (i < min) min = i;
        return min;
    }

    public int[] solution(int N) {
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) ans[i] = i + 1;
        return ans;
    }

    public int min_filters(int[] A) {
        PriorityQueue<Double> maxPQ = new PriorityQueue<>(Comparator.comparingDouble(i -> -i));
        double initialSum = 0;
        for (int i : A) {
            maxPQ.add((double) i);
            initialSum += i;
        }
        int filters = 0;
        for (double curSum = initialSum; curSum > initialSum / 2; filters++) {
            double max_polution = maxPQ.remove();
            curSum -= max_polution / 2;
            maxPQ.add(max_polution / 2);
        }
        return filters;
    }

    public int minimizeMemory(List<Integer> processes, int m) {
        // sliding window: find size m sub-array with max sum - running time = O(N), space = O(1)
        int n = processes.size(), cur_window = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            if (i < m)
                cur_window += processes.get(i);
            sum += processes.get(i);
        }
        int max_window = cur_window;
        for (int i = m; i < n; i++) {
            cur_window = cur_window - processes.get(i - m) + processes.get(i);
            max_window = Math.max(cur_window, max_window);
        }
        return sum - max_window;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 4, 8, 13, 20);
        a_parth1 solution = new a_parth1();
        System.out.println(solution.minimizeMemory(list, 2));
    }
}
