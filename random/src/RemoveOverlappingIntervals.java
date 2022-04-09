import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class RemoveOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        // sort the intervals by start points
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        // consider only 2 intervals at a time: the current and the previous
        int[] prevInterval = intervals[0];
        int removed = 0;

        for (int i = 1; i < intervals.length; i++) {
            int[] curInterval = intervals[i];
            // there are 3 cases:
            if (curInterval[0] >= prevInterval[1]) // 1. no overlap between curInterval & prevInterval
                prevInterval = curInterval;
            else if (curInterval[1] <= prevInterval[1]) { // 2. overlap; curInterval is a sub-interval of preInterval
                removed++;
                prevInterval = curInterval;
            } else  // 3. overlap; curInterval ends later than prevInterval
                removed++;
        }

        return removed;
    }
}
