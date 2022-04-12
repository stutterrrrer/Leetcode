import java.util.Arrays;

public class ArrayProductExceptSelf_238 {
	// using division, which is banned from this problem... but it's the obvious answer though
	public int[] productExceptSelf(int[] nums) {
		int zeroCount = 0, nonZeroProduct = 1;
		for (int num : nums) {
			if (num == 0) zeroCount++;
			else nonZeroProduct *= num;
		}

		int[] output = new int[nums.length];
		final int n = output.length;
		if (zeroCount > 1) {
			Arrays.fill(output, 0);
		} else if (zeroCount == 1) {
			for (int i = 0; i < n; i++)
				output[i] = nums[i] == 0 ? nonZeroProduct : 0;
		} else {
			for (int i = 0; i < n; i++)
				output[i] = nonZeroProduct / nums[i];
		}
		return output;
	}
}
