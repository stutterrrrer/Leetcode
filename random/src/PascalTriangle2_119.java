import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PascalTriangle2_119 {
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) return new ArrayList<>(Collections.singleton(1));
        Integer[] prevRow = {1, 1}, curRow = new Integer[1];
        if (rowIndex == 1) return Arrays.asList(prevRow);

        for (int i = 2; i <= rowIndex; i++) {
            curRow = new Integer[i + 1];
            curRow[0] = 1;
            curRow[i] = 1;
            for (int j = 1; j < i; j++)
                curRow[j] = prevRow[j - 1] + prevRow[j];
            prevRow = curRow;
        }
        return Arrays.asList(curRow);
    }

    public static void main(String[] args) {
        PascalTriangle2_119 pascalRow = new PascalTriangle2_119();
        int i = 4;
        System.out.println(pascalRow.getRow(i));
    }
}
