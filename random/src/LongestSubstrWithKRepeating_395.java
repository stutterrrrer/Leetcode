import java.util.HashMap;

public class LongestSubstrWithKRepeating_395 {
    char[] chars;
    int min_freq;

    public int longestSubstring(String s, int k) {
        chars = s.toCharArray();
        min_freq = k;
        return maxValidLength(0, chars.length);
    }

    private int maxValidLength(int bgn, int end_excl) {
        // base case 1: not enough length
        if (end_excl - bgn < min_freq) return 0;
        // count char frequencies
        HashMap<Character, Integer> charFreq = new HashMap<>();
        for (int i = bgn; i < end_excl; i++) {
            char c = chars[i];
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }
        int charFreqSize = charFreq.size();
        // remove entry if the char's frequency is less than min_freq
        charFreq.entrySet().removeIf(entry -> entry.getValue() < min_freq);
        // rename appropriately
        HashMap<Character, Integer> validChars = charFreq;

        // base case 2: if the entire list is valid: return this substring's length
        if (charFreqSize == validChars.size()) return end_excl - bgn;

        // if has invalid chars, go into recursive case
        int endOfFirst = bgn;
        while (endOfFirst < end_excl && validChars.containsKey(chars[endOfFirst])) endOfFirst++;
        int bgnOfSecond = endOfFirst;
        while (bgnOfSecond < end_excl && !validChars.containsKey(chars[bgnOfSecond])) bgnOfSecond++;
        return Math.max(maxValidLength(bgn, endOfFirst), maxValidLength(bgnOfSecond, end_excl));
    }

    public static void main(String[] args) {
        LongestSubstrWithKRepeating_395 solver = new LongestSubstrWithKRepeating_395();
        String s = "ababbc";
        int k = 2;
        System.out.println(solver.longestSubstring(s, k));
    }
}
