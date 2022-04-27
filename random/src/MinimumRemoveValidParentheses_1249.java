import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class MinimumRemoveValidParentheses_1249 {
    public String minRemoveToMakeValid1(String s) {
        // use an index stack, 2 passes
        Stack<Integer> leftParenIndices = new Stack<>();
        Set<Integer> indicesToRemove = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') leftParenIndices.add(i);
            else if (c == ')') {
                if (leftParenIndices.isEmpty()) indicesToRemove.add(i);
                else leftParenIndices.pop();
            }
        }
        while (!leftParenIndices.isEmpty()) indicesToRemove.add(leftParenIndices.pop());

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            if (!indicesToRemove.contains(i)) ans.append(s.charAt(i));
        return ans.toString();
    }

    public String minRemoveToMakeValid2(String s) {
        // no stack, 2 passes
        int unmatchedLeftParen = 0, remValidLeftParen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') unmatchedLeftParen++;
            else if (c == ')' && unmatchedLeftParen > 0) {
                remValidLeftParen++;
                unmatchedLeftParen--;
            }
        }

        StringBuilder ans = new StringBuilder();
        int remValidRightParen = remValidLeftParen;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(')
                if (remValidLeftParen > 0) remValidLeftParen--;
                else continue;
            else if (c == ')')
                if (remValidRightParen > remValidLeftParen) remValidRightParen--;
                else continue;
            ans.append(c);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String input = "))(()abc()";
        MinimumRemoveValidParentheses_1249 solver = new MinimumRemoveValidParentheses_1249();
        System.out.println(solver.minRemoveToMakeValid1(input));
        System.out.println(solver.minRemoveToMakeValid2(input));
    }
}
