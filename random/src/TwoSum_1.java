import java.util.HashMap;
import java.util.Map;

public class TwoSum_1 {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> numIndices = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (numIndices.containsKey(target - nums[i]))
				return new int[]{numIndices.get(target - nums[i]), i};
			numIndices.put(nums[i], i);
		}
		return null;
	}
}
