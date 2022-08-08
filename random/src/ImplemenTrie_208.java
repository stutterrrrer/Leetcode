class Trie {
    Trie[] next;
    boolean isWord;

    public Trie() {
        next = null; // not yet a prefix
        isWord = false;
    }

    public void insert(String word) {
        Trie cur = this;
        char[] charArr = word.toCharArray();
        for (char c : charArr) {
            if (cur.next == null) cur.next = new Trie[26];
            int slot = c - 'a';
            if (cur.next[slot] == null) cur.next[slot] = new Trie();
            cur = cur.next[slot];
        }
        cur.isWord = true;
    }

    private Trie furthestNode(String word) {
        Trie cur = this;
        char[] charArr = word.toCharArray();
        for (char c : charArr) {
            int slot = c - 'a';
            if (cur.next == null || cur.next[slot] == null) return null;
            cur = cur.next[slot];
        }
        return cur;
    }

    public boolean search(String word) {
        Trie node = furthestNode(word);
        return (node != null && node.isWord);
    }

    public boolean startsWith(String prefix) {
        Trie node = furthestNode(prefix);
        return node != null; // either it is a word, or it has next (prefix); either way return true;
    }
}