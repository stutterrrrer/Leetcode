import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak_139 {

    private static class TrieNode {
        private TrieNode[] next;
        private boolean isWord;
        private final int depth; // depth of a node is the length of the word

        public TrieNode(int depth) {
            this.depth = depth;
        }

        public int getDepth() {
            return depth;
        }

        public void insert(String word) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a'; // constraint: word has only lower-case letters
                if (cur.next == null) cur.next = new TrieNode[26]; // new array of Null s
                if (cur.next[c] == null) cur.next[c] = new TrieNode(cur.depth + 1);
                cur = cur.next[c];
            } // upon termination, cur points to the node representing the last letter of the word
            cur.isWord = true;
        }

        public TrieNode nextMatch(String str) { // str may be a substring, invoking instance may not be root
            assert this.depth == 0 || this.isWord; // the invoking instance is either root or a complete word.
            TrieNode cur = this; // treating the invoking instance as the root of a new dictionary
            for (int i = 0; i < str.length(); i++) {
                if (cur.next == null) return null;
                cur = cur.next[str.charAt(i) - 'a'];
                if (cur == null) return null;
                if (cur.isWord) return cur;
            }
            return null; // when end of string is not yet end of a complete word
        }
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        TrieNode dictRoot = new TrieNode(0);
        for (String word : wordDict)
            dictRoot.insert(word);
        Set<Integer> ruledOutBgns = new HashSet<>(); // memoization during backtracking
        return matchWords(s, 0, dictRoot, ruledOutBgns);
    }

    private boolean matchWords(String s, int bgn, TrieNode root, Set<Integer> ruledOutBgns) {
        if (bgn == s.length()) return true; // matched to end of string in previous call
        if (ruledOutBgns.contains(bgn)) return false; // if we've been down this path already in vain
        TrieNode nextMatch = root.nextMatch(s.substring(bgn)); // the first (shortest) match starting s[bgn]
        while (nextMatch != null) {
            int matchedLen = bgn + nextMatch.getDepth();
            if (matchWords(s, matchedLen, root, ruledOutBgns)) // try next substring starting s[matchedLen]
                return true; // then if this path doesn't work -> backtrack to
            nextMatch = nextMatch.nextMatch(s.substring(matchedLen)); // find next(longer) substr(bgn, > matchedLen);
        }
        ruledOutBgns.add(bgn); // when there's no next match
        return false;
    }

    public static void main(String[] args) {
        String[] dict = {"cats", "dog", "sand", "and", "cat"};
        String s = "catsandog";
        WordBreak_139 solver = new WordBreak_139();
        System.out.println(solver.wordBreak(s, Arrays.stream(dict).toList()));
    }
}
