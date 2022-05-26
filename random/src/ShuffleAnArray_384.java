import java.util.Arrays;
import java.util.Random;

public class ShuffleAnArray_384 {
    int[] original;
    int n;
    Random random;

    public ShuffleAnArray_384(int[] nums) {
        original = nums;
        n = original.length;
        random = new Random();
    }

    public int[] shuffle() {
        int[] cur = Arrays.copyOf(original, n);
        for (int i = 0; i < n; i++) {
            int j = random.nextInt(i + 1);
            int temp = cur[j];
            cur[j] = cur[i];
            cur[i] = temp;
        }
        return cur;
    }

    public int[] reset() {
        return Arrays.copyOf(original, n);
    }
}
