public class FirstBadVersion_278 {
    int firstBad;

    public FirstBadVersion_278(int firstBad) {
        this.firstBad = firstBad;
    }

    public int firstBadVersion(int n) {
        int bgn = 1;
        for (int end = n; bgn < end; ) {
            // mind the integer over-flow problem
            int mid = bgn + (end - bgn) / 2;
            if (!isBadVersion(mid)) bgn = mid + 1; // first bad is on the right of mid
            // mid might be first bad; in which case all subsequent mid(s) would be good versions
            // so the left (bgn) pointer would keep approaching first bad, until (bgn == first bad index)
            else end = mid;
        }
        return bgn;
    }

    private boolean isBadVersion(int i) {
        return i >= firstBad;
    }

    public static void main(String[] args) {
        int n = 2126753390;
        int firstBad = 1702766719;
        FirstBadVersion_278 solver = new FirstBadVersion_278(firstBad);
        System.out.println(solver.firstBadVersion(n));
    }
}
