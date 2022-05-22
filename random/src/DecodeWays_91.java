public class DecodeWays_91 {
    public int numDecodings(String s) {
        int n = s.length();
        int[] decodeWaysStartingBgnPlus = new int[3];
        decodeWaysStartingBgnPlus[1] = 1; // for the 2 base cases -> bgn = n-1 & n-2
        for (int bgn = n - 1; bgn >= 0; bgn--) {
            decodeWaysStartingBgnPlus[0] = decodeDigitsToSingleLetter(bgn, bgn, s) * decodeWaysStartingBgnPlus[1]
                    + decodeDigitsToSingleLetter(bgn, bgn + 1, s) * decodeWaysStartingBgnPlus[2];
            decodeWaysStartingBgnPlus[2] = decodeWaysStartingBgnPlus[1]; // update for bgn--;
            decodeWaysStartingBgnPlus[1] = decodeWaysStartingBgnPlus[0];
        }
        return decodeWaysStartingBgnPlus[0];
    }

    private int decodeDigitsToSingleLetter(int bgn, int end, String s) {
        if (end == s.length() // for base case bgn = n - 1
                || s.charAt(bgn) == '0') return 0; // 0 can't be the only or the first digit
        else if (bgn == end) return 1; // 1 digit that's not '0' always has a corresponding letter
        else { // end == bgn + 1 && s[bgn] != 0
            int x = (s.charAt(bgn) - '0') * 10 + (s.charAt(end) - '0');
            return x <= 26 ? 1 : 0;
        }
    }
}
