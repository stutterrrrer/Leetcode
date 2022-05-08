import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FindAllAnagramsInString_438 {
    public List<Integer> findAnagrams(String s, String p) {
        int n = s.length(), k = p.length();
        HashMap<Character, Integer> wanted = new HashMap<>();
        for (int i = 0; i < k; i++) {
            char c = p.charAt(i);
            wanted.put(c, wanted.getOrDefault(c, 0) + 1);
        }
        // wanted could have negative count -> meaning we have one extra of that character
        List<Integer> ans = new LinkedList<>();
        for (int i = 0, start = 0; i < n; i++) {
            char added = s.charAt(i); // always add the window end
            wanted.put(added, wanted.getOrDefault(added, 0) - 1);
            if (wanted.get(added) == 0) wanted.remove(added);
            if (i - start + 1 > k) {
                char removed = s.charAt(start++); // start removing the window start
                wanted.put(removed, wanted.getOrDefault(removed, 0) + 1);
                if (wanted.get(removed) == 0) wanted.remove(removed);
            }
            if (wanted.isEmpty()) ans.add(start);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "abab";
        String p = "ab";
        FindAllAnagramsInString_438 solver = new FindAllAnagramsInString_438();
        System.out.println(solver.findAnagrams(s, p));
    }
}
