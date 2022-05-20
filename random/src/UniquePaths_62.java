import java.util.Arrays;

public class UniquePaths_62 {
    public int uniquePathsNaive(int height, int width) {
        int[][] pathsTo = new int[height][width];
        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++)
                pathsTo[row][col] = (row > 0 && col > 0) ? pathsTo[row - 1][col] + pathsTo[row][col - 1] : 1;
        return pathsTo[height - 1][width - 1];
    }

    public int uniquePaths(int height, int width) { // space efficient: only remembers 1 row / col at any time
        int max = Math.max(height, width), min = Math.min(height, width);
        int[] pathsTo = new int[min];
        Arrays.fill(pathsTo, 1); // either first row or first col will always be 1
        for (int i = 1; i < max; i++)
            for (int j = 1; j < min; j++)
                pathsTo[j] += pathsTo[j - 1]; // overwrites itself
        return pathsTo[min - 1];
    }
}
