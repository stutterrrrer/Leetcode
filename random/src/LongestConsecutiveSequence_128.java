import java.util.HashMap;
import java.util.TreeMap;

public class LongestConsecutiveSequence_128 {

	private static class Sequence {
		int min;
		int max;

		public Sequence(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}

	public int longestConsecutive(int[] nums) {
		// 2 maps pointing to the same set of sequences.
		HashMap<Integer, Sequence> minMap = new HashMap<>();
		TreeMap<Integer, Sequence> maxTreeMap = new TreeMap<>();

		// populate & merge the sequences in the maps
		for (int num : nums) {
			// if this number becomes the new min of an existing sequence:
			if (minMap.containsKey(num + 1)) {
				Sequence newSeq = minMap.remove(num + 1);
				newSeq.min = num;
				minMap.put(newSeq.min, newSeq);
				// this simultaneously modifies the Sequence instance in maxTreeMap, but the key there didn't change.

				// merge 2 sequences:
				if (maxTreeMap.containsKey(num - 1)) {
					// remove the smaller sequence from both maps,
					Sequence smallerSeq = maxTreeMap.remove(num - 1);
					minMap.remove(smallerSeq.min);
					// then update newSeq key in minMap only, because newSeq's max didn't change in this merge
					minMap.remove(newSeq.min);
					newSeq.min = smallerSeq.min;
					minMap.put(newSeq.min, newSeq);
				}
			} else if (maxTreeMap.containsKey(num - 1)) { // if this number becomes the new max of an existing sequence
				Sequence newSeq = maxTreeMap.remove(num - 1);
				newSeq.max = num;
				maxTreeMap.put(newSeq.max, newSeq);
			} else { // if this number doesn't modify an existing sequence:
				// first check if num is included in an existing sequence:
				final Integer largerMax = maxTreeMap.ceilingKey(num);
				if (largerMax == null || maxTreeMap.get(largerMax).min > num) {
					// if num isn't included, create new sequence:
					Sequence newSeq = new Sequence(num, num);
					minMap.put(newSeq.min, newSeq);
					maxTreeMap.put(newSeq.max, newSeq);
				}
			}
		}

		// find the longest sequence
		int longest = 0;
		for (Sequence seq : minMap.values())
			longest = Math.max(longest, seq.max - seq.min + 1);

		return longest;
	}

	public static void main(String[] args) {
		LongestConsecutiveSequence_128 solver = new LongestConsecutiveSequence_128();
		int[] nums = {100, 4, 200, 1, 3, 2};
		System.out.println(solver.longestConsecutive(nums));
		nums = new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
		System.out.println(solver.longestConsecutive(nums));
	}
}
