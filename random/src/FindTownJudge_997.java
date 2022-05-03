import java.util.HashSet;
import java.util.Set;

public class FindTownJudge_997 {
    public int findJudge(int n, int[][] trust) {
        Set<Integer> distrustingPeople = new HashSet<>();
        for (int i = 1; i <= n; i++) distrustingPeople.add(i);
        int[] numberOfPeopleThatTrust = new int[n];

        for (int[] pair : trust) {
            distrustingPeople.remove(pair[0]);
            if (distrustingPeople.isEmpty()) return -1;
            numberOfPeopleThatTrust[pair[1] - 1]++;
        }
        for (int candidate : distrustingPeople) if (numberOfPeopleThatTrust[candidate - 1] == n - 1) return candidate;
        return -1;
    }

    public int findJudgeOneArray(int n, int[][] trust) {
        // saves space, but can only stop after all pairs in trust are done.
        int[] inMinusOutDegree = new int[n + 1]; // 1-index the array
        for (int[] pair : trust) {
            // the judge, if it exists, would have the max: (n - 1) - 0 = n - 1
            inMinusOutDegree[pair[0]]--;
            inMinusOutDegree[pair[1]]++;
        }
        for (int i = 1; i <= n; i++) if (inMinusOutDegree[i] == n - 1) return i;
        return -1;
    }
}
