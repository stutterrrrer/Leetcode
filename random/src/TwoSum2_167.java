import javax.swing.*;
import java.util.Arrays;

public class TwoSum2_167 {
    public int[] twoSum(int[] numbers, int target) {
        // since input array is sorted in non-descending order
        int n = numbers.length;
        int smallAddendIndex = 0, bigAddendIndex = n - 1;
        while (numbers[smallAddendIndex] + numbers[bigAddendIndex] != target) {
            int sum = numbers[smallAddendIndex] + numbers[bigAddendIndex];
            if (sum < target) {
                do smallAddendIndex++;
                while (numbers[smallAddendIndex] == numbers[smallAddendIndex - 1]); // avoid long repeated sequence
            } else {
                do bigAddendIndex--;
                while (numbers[bigAddendIndex] == numbers[bigAddendIndex + 1]);
            }
        }
        return new int[]{smallAddendIndex + 1, bigAddendIndex + 1}; // 1-indexed requirement
    }


    public int[] twoSum2(int[] numbers, int target) {
        for (int i = 0, j = numbers.length - 1; i < j; ) {
            int sum = numbers[i] + numbers[j];
            if (sum < target)
                do i++;
                while (numbers[i] == numbers[i - 1]);
            else if (sum > target)
                do j--;
                while (numbers[j] == numbers[j + 1]);
            else return new int[]{i + 1, j + 1};
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 3, 3, 7, 11, 11, 11, 15};
        int target = 9;
        for (int x : new TwoSum2_167().twoSum2(arr, target)) System.out.print(x + ", ");
    }
}
