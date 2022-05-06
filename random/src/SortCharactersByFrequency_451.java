import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SortCharactersByFrequency_451 {
    public String frequencySort(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : str) freq.put(c, freq.getOrDefault(c, 0) + 1);
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(n, Comparator.comparingInt(i -> -freq.get(i)));
        maxHeap.addAll(freq.keySet());
        for (int bgnOfCurChar = 0; !maxHeap.isEmpty();) {
            char curChar = maxHeap.remove();
            int frequency = freq.remove(curChar);
            for (int i = bgnOfCurChar; i < bgnOfCurChar + frequency; i++) str[i] = curChar;
            bgnOfCurChar += frequency;
        }
        return new String(str);
    }
}
