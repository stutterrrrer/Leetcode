import java.util.*;

public class MergeIntervals_56 {
    public int[][] merge(int[][] intervals) {
        // sort by start time, then compare current interval's start time with previous one's end time
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0])); // n * log n
        int n = intervals.length;
		Stack<int[]> noOverlap = new Stack<>(); // use stack over lists - fast access of the last element
		noOverlap.add(intervals[0]);
        for (int i = 1; i < n; i++) {
            final int[] lastInterval = noOverlap.peek();
            if (intervals[i][0] <= lastInterval[1])  // when there's overlap:
                lastInterval[1] = Math.max(intervals[i][1], lastInterval[1]);
            else
                noOverlap.add(intervals[i]);
        }
        return noOverlap.toArray(new int[noOverlap.size()][]);
    }

    public static void main(String[] args) {
    }
}
