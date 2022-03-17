import java.util.*;

public class ThreeSum_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> solutionTriplets = new LinkedList<>();
        if (n < 3) return solutionTriplets;

        for (int i = 0; i < n - 2; ) {
            List<List<Integer>> triplets = findTwoSum(nums, i);
            solutionTriplets.addAll(triplets);
            do i++;
            while (nums[i] == nums[i - 1] && i < n - 1);
        }
        return solutionTriplets;
    }

    private List<List<Integer>> findTwoSum(int[] nums, int indexOfFirstAddend) {
        int n = nums.length;
        int target = -nums[indexOfFirstAddend];
        // search for the 2-sum target starting at nums[indexOfFirstAdded + 1]
        int smallAddendIndex = indexOfFirstAddend + 1, bigAddendIndex = n - 1;
        List<List<Integer>> triplets = new LinkedList<>();

        while (smallAddendIndex < bigAddendIndex) {
            final int smallAddend = nums[smallAddendIndex], bigAddend = nums[bigAddendIndex];
            int sum = smallAddend + bigAddend;
            if (sum == target)
                triplets.add(new LinkedList<>(Arrays.asList(nums[indexOfFirstAddend], smallAddend, bigAddend)));
            if (sum > target || sum == target) {
                do bigAddendIndex--;
                while (nums[bigAddendIndex] == nums[bigAddendIndex + 1] && bigAddendIndex - 1 > 0);
            }
            if (sum < target || sum == target) {
                do smallAddendIndex++;
                while (nums[smallAddendIndex] == nums[smallAddendIndex - 1] && smallAddendIndex + 1 < n);
            }
        }
        return triplets;
}

    public static void main(String[] args) {
        ThreeSum_15 solver = new ThreeSum_15();

        int[] nums = {-1, 0, 1, 2, -1, -4, 0, 0};
        int[] nums2 = {0, 0, 0};
        int[] nums3 = {-2, -3, 0, 0, -2};

        for (List<Integer> triplet : solver.threeSum(nums2))
            System.out.println(triplet);
    }
}
