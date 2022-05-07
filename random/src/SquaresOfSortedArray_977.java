import java.util.Arrays;

public class SquaresOfSortedArray_977 {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] squared = new int[n];
        int nextInsert = n - 1;
        for (int i = 0, j = n - 1; i<= j;) {
            if (Math.abs(nums[j]) > Math.abs(nums[i]))
                squared[nextInsert--] = nums[j] * nums[j--];
            else
                squared[nextInsert--] = nums[i] * nums[i++];
        }
        return squared;
    }

    public static void main(String[] args) {
        int[] input = {-22, -20, -13, -8, -2};
        SquaresOfSortedArray_977 solver = new SquaresOfSortedArray_977();
        Arrays.stream(solver.sortedSquares(input)).forEach(i -> System.out.print(i + ", "));
    }
}
