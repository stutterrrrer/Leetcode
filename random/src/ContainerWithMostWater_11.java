public class ContainerWithMostWater_11 {
    int maxArea(int[] heights) {
        int maxArea = 0;
        for (int left = 0, right = heights.length - 1; left < right;) { // start from max width
            int area = (right - left) * Math.min(heights[left], heights[right]);
            maxArea = Math.max(maxArea, area);
            // only way shrinking width increases area - by increasing the min height: move the shorter end GREEDILY
            if (heights[left] <= heights[right]) // see Notion page for proof when the 2 heights are equal
                left++;
            else right--;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] input = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new ContainerWithMostWater_11().maxArea(input));
    }
}
