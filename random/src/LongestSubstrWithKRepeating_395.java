import java.util.HashMap;
import java.util.Map;

public class LongestSubstrWithKRepeating_395 {

    public int longestSubstring(String s, int k) {
        HashMap<Character, Integer> validChars = new HashMap<>();
        char[] sequence = s.toCharArray();
        // count all char frequencies
        for (char c : sequence)
            validChars.put(c, validChars.getOrDefault(c, 0) + 1);
        // leave only the valid characters in the hash map, where their frequencies >= k
        removeInvalidChars(validChars, k);

        int bgn = 0, end = 0;
        int maxValidLength = 0;
        while (end < sequence.length) {
            HashMap<Character, Integer> curWindow = new HashMap<>();
            // keep expanding current window from the tail
            while (end < sequence.length && validChars.containsKey(sequence[end])) {
                char curChar = sequence[end];
                curWindow.put(curChar, curWindow.getOrDefault(curChar, 0) + 1);
                end++;
            }
            // either encountered an invalid key or went through the whole string:
            //todo: keep checking from the start of the window to remove invalid characters
            // then if the remaining current window is valid, update max length
            if (!curWindow.isEmpty() && curWindow.values().stream().allMatch(i -> i >= k))
                maxValidLength = Math.max(end - bgn, maxValidLength);
            // update validChars - by removing the frequencies contained in this window
            for (char c : curWindow.keySet())
                validChars.put(c, validChars.get(c) - curWindow.get(c));
            removeInvalidChars(validChars, k);
            // start new window
            bgn = end + 1;
            end = bgn;
        }
        return maxValidLength;
    }
    private void removeInvalidChars(HashMap<Character, Integer> charFrequencies, int frequencyThreshold) {
        charFrequencies.entrySet().removeIf(entry -> entry.getValue() < frequencyThreshold);
    }

    public static void main(String[] args) {
        LongestSubstrWithKRepeating_395 solver = new LongestSubstrWithKRepeating_395();
        String s = "bbaaacbd";
        int k = 3;
        System.out.println(solver.longestSubstring(s, k));
    }
}
