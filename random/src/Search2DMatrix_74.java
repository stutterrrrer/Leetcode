public class Search2DMatrix_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int bgn = 0, end = rows * cols - 1; bgn <= end;) {
            int mid = bgn + (end - bgn) / 2;
            int num = matrix[mid / cols][mid % cols];
            if (target > num) bgn = mid + 1;
            else if (target < num) end = mid - 1;
            else return true;
        }
        return false;
    }
}
