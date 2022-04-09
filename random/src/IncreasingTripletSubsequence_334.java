import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class IncreasingTripletSubsequence_334 {
	public boolean increasingTriplet(int[] nums) {
		TreeSet<Integer> oneNumSequences = new TreeSet<>();
		// the key in the two-num-sequence is the larger number in the sequence.
		TreeSet<Integer> twoNumSequences = new TreeSet<>();
		for (int num : nums) {
			// if this number is the third number of any increasing sequence
			if (twoNumSequences.lower(num) != null) return true;
			// if this number is the second number of any increasing sequence
			else if (oneNumSequences.lower(num) != null) twoNumSequences.add(num);
			else oneNumSequences.add(num); // if this number is neither
		}
		return false;
	}

	public static void main(String[] args) {
		int[] arr1 = {1, 2, 3, 4, 5};
		int[] arr2 = {5, 4, 3, 2, 1};
		int[] arr3 = {2, 1, 5, 0, 4, 6};
		int[] arr4 = {1, 5, 0, 4, 1, 3};
		IncreasingTripletSubsequence_334 solver = new IncreasingTripletSubsequence_334();
		System.out.println(
				solver.increasingTriplet(arr4));
	}
}
