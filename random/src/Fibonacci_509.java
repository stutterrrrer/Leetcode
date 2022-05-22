public class Fibonacci_509 {

    public int fibRecur(int n) {
        if (n < 2) return n;
        return fibRecur(n - 1) + fibRecur(n - 2);
    }

    public int fibRecurMemo(int n) {
        int[] memo = new int[n + 1];
        return recurMemo(n, memo);
    }

    private int recurMemo(int n, int[] memo) {
        if (n < 2) memo[n] = n;
        else memo[n] = (memo[n - 1] != 0 ? memo[n - 1] : recurMemo(n - 1, memo))
                + memo[n - 2]; // after recurMemo[n - 1], memo[n - 2] would have been updated.
        return memo[n];
    }

    public int fibBottomUp(int n) {
        if (n < 2) return n;
        int[] fib = new int[n + 1];
        fib[1] = 1; // fib[0] initialized = 0, so both base cases taken care of
        for (int i = 2; i <= n; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
        return fib[n];
    }

    public int fibBottomUpOptimized(int n) {
        if (n < 2) return n;
        int[] fibOfCurMinus = new int[3];
        fibOfCurMinus[1] = 1; // first cur = 2; base cases fib[1] = 1, fib[0] = 0 (initialized)
        for (int cur = 2; cur <= n; cur++) {
            fibOfCurMinus[0] = fibOfCurMinus[1] + fibOfCurMinus[2];
            fibOfCurMinus[2] = fibOfCurMinus[1];
            fibOfCurMinus[1] = fibOfCurMinus[0];
        }
        return fibOfCurMinus[0];
    }
}