import java.util.HashSet;

public class LeastPerfectSquares_279 {
    public int numSquares(int n) {
        // HashSet of perfect squares: 1, 4, 9
        HashSet<Integer> squares = new HashSet<>();
        for (int i = 1; ; i++) {
            final int square = i * i;
            if (square <= n) squares.add(square);
            else break;
        }

        // memoization array:
        int[] leastPerfectSquares = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (squares.contains(i)) leastPerfectSquares[i] = 1; // is a perfect square
            else {
                int min = i; // all 1^2 -> all 1s.
                for (int square : squares) {
                    if (square > i) continue;
                    int numOfSquares = leastPerfectSquares[i - square] + 1;
                    min = Math.min(min, numOfSquares);
                }
                leastPerfectSquares[i] = min;
            }
        }
        return leastPerfectSquares[n];
    }

    public static void main(String[] args) {
        LeastPerfectSquares_279 solver = new LeastPerfectSquares_279();
        System.out.println(solver.numSquares(14));
    }
}
