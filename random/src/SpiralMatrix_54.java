import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix_54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int h = matrix.length, w = matrix[0].length;
        List<Integer> spiral = new LinkedList<>();
        // start from outer-most rectangle, then shrink it one by one
        for (int curRecUpperLeft = 0; curRecUpperLeft * 2 < h && curRecUpperLeft * 2 < w; curRecUpperLeft++) {
            int left = curRecUpperLeft, top = curRecUpperLeft, right = w - 1 - curRecUpperLeft, bottom = h - 1 - top;
            for (int i = left; i <= right; i++) spiral.add(matrix[top][i]); // top row including right-most
            for (int i = top + 1; i <= bottom; i++) spiral.add(matrix[i][right]); // right column excl top
            if (top != bottom)
                for (int i = right - 1; i >= left; i--) spiral.add(matrix[bottom][i]); // bottom row except right-most
            if (left != right)
                for (int i = bottom - 1; i > top; i--) spiral.add(matrix[i][left]); // left column excl top & bottom
        }
        return spiral;
    }

    public int[][] generateMatrix(int n) {
        int[][] spiralMatrix = new int[n][n];
        int curNum = 1;
        for (int curRecUpperLeft = 0; curNum <= n * n; curRecUpperLeft++) {
            int left = curRecUpperLeft, top = curRecUpperLeft, right = n - 1 - curRecUpperLeft, bottom = n - 1 - top;
            for (int i = left; i <= right; i++) spiralMatrix[top][i] = curNum++; // top row including right-most
            for (int i = top + 1; i <= bottom; i++) spiralMatrix[i][right] = curNum++; // right column excl top
            if (top != bottom)
                for (int i = right - 1; i >= left; i--) spiralMatrix[bottom][i] = curNum++; // bottom row except right-most
            if (left != right)
                for (int i = bottom - 1; i > top; i--) spiralMatrix[i][left] = curNum++; // left column excl top & bottom
        }
        return spiralMatrix;
    }

    public static void main(String[] args) {
        int h = 4, w = 3;
        int[][] matrix = new int[h][w];

        int curNum = 1;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                matrix[i][j] = curNum++;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        SpiralMatrix_54 spiral = new SpiralMatrix_54();
        System.out.println(spiral.spiralOrder(matrix));
    }
}
