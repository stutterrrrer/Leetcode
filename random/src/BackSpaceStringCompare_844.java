public class BackSpaceStringCompare_844 {
    public boolean backspaceCompare(String s, String t) {
        int sPt = s.length() - 1, tPt = t.length() - 1;
        while (sPt >= 0 || tPt >= 0) { // if only one string has remaining chars: check if they're backspaced
            sPt = nextEffectiveIndex(s, sPt);
            tPt = nextEffectiveIndex(t, tPt);
            int sChar = sPt >= 0 ? s.charAt(sPt--) : -1; // -1: for edge case: "a#a" vs "a"
            int tChar = tPt >= 0 ? t.charAt(tPt--) : -1;
            if (sChar != tChar) return false;
        }
        return true;
    }

    private int nextEffectiveIndex(String str, int pt) {
        while (pt >= 0 && str.charAt(pt) == '#') {
            int unusedBackSpace = 0;
            do unusedBackSpace += (str.charAt(pt--) == '#') ? 1 : -1; // mimics a stack
            while (unusedBackSpace > 0 && pt >= 0);
        }
        return pt; // pt may be negative
    }

    public static void main(String[] args) {
        String s = "a#aca#b##", t = "aa#c#"; // both strings are "a"
        System.out.println(new BackSpaceStringCompare_844().backspaceCompare(s, t));
    }
}
