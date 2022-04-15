import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PartitionLabels_763 {
    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> lastOccurrences = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) lastOccurrences.put(s.charAt(i), i);

        List<Integer> partitionSizes = new LinkedList<>();
        for (int bgn = 0; bgn < n;) {
            int end = lastOccurrences.get(s.charAt(bgn));
            // this partition:
            for (int i = bgn; i <= end; i++)
                end = Math.max(end, lastOccurrences.get(s.charAt(i)));
            partitionSizes.add(end - bgn + 1);
            bgn = end + 1;
        }
        return partitionSizes;
    }

	public static void main(String[] args) {
		PartitionLabels_763 solver = new PartitionLabels_763();
		System.out.println(solver.partitionLabels("eccbbbbdec"));
	}
}
