import java.util.Stack;

public class MinimiumRemoveValidParentheses_1249 {
	public String minRemoveToMakeValid1(String s) {
		// use a index stack and StringBuilder.removeCharAt() method, which takes O(N) time.
		Stack<Integer> leftParenIndices = new Stack<>();
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') leftParenIndices.add(i);
			else if (c == ')') {
				if (leftParenIndices.isEmpty()) continue; // don't pop nor append
				else leftParenIndices.pop();
			}
			ans.append(c);
		}
		while (!leftParenIndices.isEmpty()) ans.deleteCharAt(leftParenIndices.pop());
		return ans.toString();
	}

	public String minRemoveToMakeValid2(String s) {
		// no stack, 2 passes, all O(1) operations
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
			if ((c == '(' && remValidLeftParen-- <= 0) ||
					(c == ')' && remValidRightParen <= Math.max(remValidLeftParen, 0))) {
				continue;
			}
			ans.append(c);
		}
		return ans.toString();
	}

	public static void main(String[] args) {
		String input = "lee(t(c)o)de)";
		
	}
}
