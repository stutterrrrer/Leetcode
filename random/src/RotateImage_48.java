public class RotateImage_48 {
    public void rotate(int[][] matrix) {
        // matrix[x][y] -> matrix[y][n - 1 - x]
        int n = matrix.length;
        // start from outer-most square, then shrink down to smaller square
        for (int curSquareUpperLeft = 0; curSquareUpperLeft * 2 < n; curSquareUpperLeft++) {
            // one pass through the top row of the current square, !! except the last column
            // because that's been replaced already by original matrix[curSquareUpperLeft][curSquareUpperLeft]
            for (int colInTopRow = curSquareUpperLeft; colInTopRow < n - curSquareUpperLeft - 1; colInTopRow++) {
                int replacementRow = curSquareUpperLeft, replacementCol = colInTopRow;
                int replacement = matrix[replacementRow][replacementCol];
                for (int i = 0; i < 4; i++) { // 4 replacements per round
                    int replacedRow = replacementCol, replacedCol = n - 1 - replacementRow;
                    int replaced = matrix[replacedRow][replacedCol];
                    matrix[replacedRow][replacedCol] = replacement;
                    // update for next replacing:
                    replacement = replaced;
                    replacementRow = replacedRow;
                    replacementCol = replacedCol;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] matrix = new int[n][n];

        // assign & print original
        int curNum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = curNum++;
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        RotateImage_48 rotate = new RotateImage_48();
        rotate.rotate(matrix);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }
}
