import java.util.Arrays;

public class MaxCollinearPoints_149 {
    public int maxPoints(int[][] points) {
        int n = points.length, max = 0;
        if (n == 1) return 1; // constraint: points.length >= 1
        double[] slopeBtwnAnchorAndPoint = new double[n];
        for (int anchor = 0; anchor < n; anchor++) {
            int x0 = points[anchor][0], y0 = points[anchor][1];
            for (int i = anchor + 1; i < n; i++) { // ignore points before this anchor - considered in prev iterations
                if (points[i][0] == x0) slopeBtwnAnchorAndPoint[i] = Double.MAX_VALUE; // divisor can't be 0
                else slopeBtwnAnchorAndPoint[i] = (double) (points[i][1] - y0) / (points[i][0] - x0);
            }
            Arrays.sort(slopeBtwnAnchorAndPoint, anchor + 1, n);
            int maxPointsFromAnchor = 2; // anchor & any other point
            for (int i = anchor + 2; i <= n; i++) { // i = n: when last points till pts[n - 1] form a line
                if (i != n && slopeBtwnAnchorAndPoint[i] == slopeBtwnAnchorAndPoint[i - 1]) maxPointsFromAnchor++;
                else {
                    max = Math.max(max, maxPointsFromAnchor);
                    maxPointsFromAnchor = 2;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[][] points = {{0, 1}, {0, 0}, {0, 4}, {0, -2}, {0, -1}, {0, 3}, {0, -4}};
        MaxCollinearPoints_149 solver = new MaxCollinearPoints_149();
        System.out.println(solver.maxPoints(points));
    }
}
