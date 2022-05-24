public class LongestCommonSubsequence_1143 {

    public int longestCommonSubsequence(String text1, String text2) {
        char[] s1 = text1.toCharArray(), s2 = text2.toCharArray();
        int l1 = s1.length, l2 = s2.length;
        int[][] longestCommon = new int[l1 + 1][l2 + 1]; // extra row & col: incorporate base cases into recursive cases
        for (int bgn1 = l1 - 1; bgn1 >= 0; bgn1--)
            for (int bgn2 = l2 - 1; bgn2 >= 0; bgn2--)
                if (s1[bgn1] == s2[bgn2])
                    longestCommon[bgn1][bgn2] = 1 + longestCommon[bgn1 + 1][bgn2 + 1];
                else longestCommon[bgn1][bgn2] = Math.max(
                        longestCommon[bgn1][bgn2 + 1],
                        longestCommon[bgn1 + 1][bgn2]
                );
        return longestCommon[0][0];
    }
}
