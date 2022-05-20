public class LongestPalindromicSubstring_5 {

    public String longestPalindromeCenterCharArray(String string) {
        int n = string.length(), max = 0, bgn = 0;
        char[] s = string.toCharArray();
        for (int leftCen = 0, rightCen = 0; rightCen < n; ) {
            if (s[leftCen] != s[rightCen]) leftCen = rightCen;
            int wing = 0;
            while (leftCen - wing - 1>= 0 && rightCen + wing + 1 < n
                    && s[leftCen - wing - 1] == s[rightCen + wing + 1])
                wing++;
            int length = (rightCen + wing) - (leftCen - wing) + 1;
            if (length > max) {
                max = length;
                bgn = leftCen - wing;
            }
            if (leftCen < rightCen) leftCen++; // so s[rightCen] can try being a single center
            else rightCen++; // each char will have a chance to be both left center and right center
        }
        return string.substring(bgn, bgn + max);
    }

    public String longestPalindromeCenter(String s) {
        int n = s.length(), max = 0, bgn = 0;
        for (int leftCen = 0, rightCen = 0; rightCen < n; ) {
            if (s.charAt(leftCen) != s.charAt(rightCen)) leftCen = rightCen;
            int wing = 0;
            while (leftCen - wing - 1>= 0 && rightCen + wing + 1 < n
                    && s.charAt(leftCen - wing - 1) == s.charAt(rightCen + wing + 1))
                wing++;
            int length = (rightCen + wing) - (leftCen - wing) + 1;
            if (length > max) {
                max = length;
                bgn = leftCen - wing;
            }
            if (leftCen < rightCen) leftCen++; // so s[rightCen] can try being a single center
            else rightCen++; // each char will have a chance to be both left center and right center
        }
        return s.substring(bgn, bgn + max);
    }

    public String longestPalindromeCenterMessy(String s) {
        char[] str = s.toCharArray();
        int n = str.length, longest = 1, bgnIndex = 0;
        for (int leftCenter = 0; leftCenter < n - 1; leftCenter++) {
            int rightCenter = str[leftCenter] == str[leftCenter + 1] ? leftCenter + 1 : leftCenter;
            for (int right = leftCenter; right <= rightCenter; right++) { // this loop has 1 or 2 iterations.
                int lenOfCurPalindrome = right - leftCenter + 1;
                for (int wing = 1; leftCenter - wing >= 0 && right + wing <= n - 1; wing++) {
                    if (str[leftCenter - wing] == str[right + wing]) lenOfCurPalindrome += 2;
                    else break;
                }
                if (lenOfCurPalindrome > longest) {
                    longest = lenOfCurPalindrome;
                    bgnIndex = leftCenter - (lenOfCurPalindrome - 1) / 2;
                }
            }
        }
        return s.substring(bgnIndex, bgnIndex + longest);
    }

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
                    if (len > longest && isPalindrome(s.substring(i - len + 1, i + 1))) {//todo: omit anagram check for common substring
                        longest = len;
                        endIndexOfS1 = i;
                    }
                }
            }
        }
        return s.substring(endIndexOfS1 - longest + 1, endIndexOfS1 + 1);
    }

    private boolean isPalindrome(String str) {
        int n = str.length();
        for (int i = 0; i <= n / 2; i++)
            if (str.charAt(i) != str.charAt(n - i - 1)) return false;
        return true;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring_5 solver = new LongestPalindromicSubstring_5();
        System.out.println(solver.longestPalindromeCenter("babad"));
    }
}
