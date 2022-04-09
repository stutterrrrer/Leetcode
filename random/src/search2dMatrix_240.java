import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class search2dMatrix_240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int curCellRow = rows - 1, curCellCol = 0;
        while (curCellRow >= 0 && curCellCol < cols) {
            int curCellValue = matrix[curCellRow][curCellCol];
            if (curCellValue > target) curCellRow--;
            else if (curCellValue < target) curCellCol++;
            else return true;
        }
        return false;
    }
}
