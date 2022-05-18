import java.util.*;

public class AllValidParentheses_22 {
    private List<String> answers;

    public List<String> generateParenthesis(int n) {
        char[] cur = new char[2 * n];
        answers = new ArrayList<>();
        fillSubStrStarting(0, n, 0, cur);
        return answers;
    }

    private void fillSubStrStarting(int bgn, int remainingLeftParen, int unmatchedLeftParen, char[] cur) {
        if (remainingLeftParen == 0 && unmatchedLeftParen == 0) answers.add(new String(cur));
        else { // maintain invariant: in the subarray [0, bgn++], #of left >= #of right (as we increment bgn)
            if (remainingLeftParen > 0) {
                cur[bgn] = '(';
                fillSubStrStarting(bgn + 1, remainingLeftParen - 1, unmatchedLeftParen + 1, cur);
            }
            if (unmatchedLeftParen > 0) {
                cur[bgn] = ')';
                fillSubStrStarting(bgn + 1, remainingLeftParen, unmatchedLeftParen - 1, cur);
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        List<String> myAnswer = new AllValidParentheses_22().generateParenthesis(n);
    }
}
