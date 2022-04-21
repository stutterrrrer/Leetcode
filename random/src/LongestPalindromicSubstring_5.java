import java.util.Locale;

public class LongestPalindromicSubstring_5 {
    public String longestPalindrome(String s) {
        String reverse = new StringBuilder(s).reverse().toString();
        // now find the longest common sub-string between s and its reverse;
        // remember to check for the edge case when the common sub-string isn't actually a palindrome in the original
        char[] s1 = s.toCharArray(), s2 = reverse.toCharArray();
        int n = s1.length, m = s2.length;

        int[][] lenOfCommSubStrEnding = new int[n][m]; // all initialized to 0
        int longest = 0, endIndexOfS1 = 0;
        // first row & column:
        for (int i = 0; i < n; i++) if (s1[i] == s2[0]) longest = (lenOfCommSubStrEnding[i][0] = 1);
        for (int j = 0; j < m; j++) if (s1[0] == s2[j]) longest = (lenOfCommSubStrEnding[0][j] = 1);
        // dynamic programming:
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (s1[i] == s2[j]) { // otherwise it'll be zero.
                    int len = (lenOfCommSubStrEnding[i][j] = lenOfCommSubStrEnding[i - 1][j - 1] + 1);
                    if (len > longest && isAnagram(s.substring(i - len + 1, i + 1))) {//todo: omit anagram check for common substring
                        longest = len;
                        endIndexOfS1 = i;
                    }
                }
            }
        }
        return s.substring(endIndexOfS1 - longest + 1, endIndexOfS1 + 1);
    }

    private boolean isAnagram(String str) {
        int n = str.length();
        for (int i = 0; i <= n / 2; i++)
            if (str.charAt(i) != str.charAt(n - i - 1)) return false;
        return true;
    }

	public static void main(String[] args) {
		LongestPalindromicSubstring_5 solver = new LongestPalindromicSubstring_5();
		System.out.println(solver.longestPalindrome("kabcdcbam"));
	}
}
