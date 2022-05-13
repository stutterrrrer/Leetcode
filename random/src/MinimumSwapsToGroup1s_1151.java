public class MinimumSwapsToGroup1s_1151 {
    public int minSwaps(int[] data) {
        int count = 0, n = data.length;
        for (int num : data) count += num;
        int minSwaps = count;
        for (int end = 0; end < count; end++) minSwaps -= data[end];
        for (int end = count, curSwaps = minSwaps; end < n; end++) {
            curSwaps -= data[end];
            curSwaps += data[end - count];
            minSwaps = Math.min(minSwaps, curSwaps);
        }
        return minSwaps;
    }

    public static void main(String[] args) {
        int[] data = {1,0,1,0,1,0,0,1,1,0,1};
        System.out.println(new MinimumSwapsToGroup1s_1151().minSwaps(data));
    }
}
