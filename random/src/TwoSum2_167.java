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
}
