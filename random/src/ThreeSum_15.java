import java.util.*;

public class ThreeSum_15 {
	public List<List<Integer>> threeSum(int[] nums) {
		//todo: solve the bug of duplicate triplets when `0` is in the triplet.
		int n = nums.length;
		Arrays.sort(nums);
		if (nums[0] > 0 || nums[n - 1] < 0) return null;
		int zeroCount = 0, firstPositive = n;
		for (int i = 0; i < n; i++) {
			if (nums[i] == 0) zeroCount++;
			else if (nums[i] > 0) {
				firstPositive = i;
				break;
			}
		}

		Map<Integer, List<int[]>> twoSumMap = new HashMap<>();
		for (int i = 0; i < firstPositive; i++)
			buildTwoSumMap(i, firstPositive, nums, twoSumMap);
		for (int i = firstPositive; i < n; i++)
			buildTwoSumMap(i, n, nums, twoSumMap);

		List<List<Integer>> triplets = new LinkedList<>();
		if (zeroCount >= 3) {
			List<Integer> triplet = new LinkedList<>();
			for (int i = 0; i < 3; i ++) triplet.add(0);
			triplets.add(triplet);
		}
		for (final int curNum : nums) {
			if (curNum == 0) continue;
			List<int[]> twoSumList = twoSumMap.getOrDefault(-curNum, null);
			if (twoSumList == null) continue;
			for (int[] addendPair : twoSumList) {
				List<Integer> triplet = new LinkedList<>();
				triplet.add(curNum); triplet.add(addendPair[0]); triplet.add(addendPair[1]);
				triplets.add(triplet);
			}
			twoSumMap.remove(-curNum);
		}

		return triplets;
	}

	private void buildTwoSumMap(int bgnIndex, int endIndex, int[] nums, Map<Integer, List<int[]>> twoSumMap) {
		int firstAddend = nums[bgnIndex];
		for (int j = bgnIndex + 1; j < endIndex; j++) {
			int secondAddend = nums[j];
			twoSumMap.putIfAbsent(firstAddend + secondAddend, new LinkedList<>());
			List<int[]> addendPairs = twoSumMap.get(firstAddend + secondAddend);
			addendPairs.add(new int[]{firstAddend, secondAddend});

			while ( j < endIndex - 1 && nums[j + 1] == nums[j]) j++;
		}
	}

	public static void main(String[] args) {
		int[] nums = {-1, 0, 1, 2, -1, -4, 0, 0, 0, 0, 1};
		ThreeSum_15 solver = new ThreeSum_15();
		for (List<Integer> triplet : solver.threeSum(nums)) {
			System.out.println(triplet);
		}
	}
}
