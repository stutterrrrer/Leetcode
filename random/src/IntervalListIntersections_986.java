import java.util.LinkedList;
import java.util.List;

public class IntervalListIntersections_986 {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int l1 = firstList.length, l2 = secondList.length;
        List<int[]> overlaps = new LinkedList<>();
        for (int pt1 = 0, pt2 = 0; pt1 < l1 && pt2 < l2; ) {
            int[] v1 = firstList[pt1], v2 = secondList[pt2];
            int bgn = Math.max(v1[0], v2[0]), // start of overlap, if there is one
                    end = Math.min(v1[1], v2[1]);
            if (bgn <= end) overlaps.add(new int[]{bgn, end});

            if (v1[1] < v2[1]) pt1++;
            else pt2++;
        }
        return overlaps.toArray(int[][]::new);
    }
}
