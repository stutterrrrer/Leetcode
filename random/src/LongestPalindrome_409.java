import java.util.HashSet;

public class LongestPalindrome_409 {
    public int longestPalindrome(String s) {
        HashSet<Character> oddAppearances = new HashSet<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            final char curChar = s.charAt(i);
            if (oddAppearances.contains(curChar)) {
                oddAppearances.remove(curChar);
                count += 2;
            } else
                oddAppearances.add(curChar);
        }
        if (!oddAppearances.isEmpty()) count++;
        return count;
    }

    public static void main(String[] args) {
        LongestPalindrome_409 solver = new LongestPalindrome_409();
        System.out.println(solver.longestPalindrome("abccccdd"));
    }
}
