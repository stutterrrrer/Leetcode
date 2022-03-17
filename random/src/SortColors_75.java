public class SortColors_75 {
    public void sortColors(int[] nums) {
        // same basic idea as key indexed counting , where radix = 3 (3 colors)
        int[] frequency = {0, 0, 0};
        for (int num : nums) frequency[num] = frequency[num] + 1;

        int numsIndex = 0;
        for (int color = 0; color < 3; color++)
            for (int xthOfThisColor = 0; xthOfThisColor < frequency[color]; xthOfThisColor++)
                nums[numsIndex++] = color;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SortColors_75 sort = new SortColors_75();
        sort.sortColors(nums);
        for (int num : nums) System.out.print(num + ", ");
    }
}
