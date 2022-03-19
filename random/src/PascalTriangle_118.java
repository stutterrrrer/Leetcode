import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PascalTriangle_118 {
    public List<List<Integer>> generate(int numRows) {
        Stack<List<Integer>> pascal = new Stack<>();
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            Integer[] curRow = new Integer[rowIndex + 1];
            curRow[0] = 1;
            curRow[rowIndex] = 1;

            if (rowIndex > 1) {
                List<Integer> prevRow = pascal.peek();
                for (int i = 1; i < rowIndex; i++)
                    curRow[i] = prevRow.get(i - 1) + prevRow.get(i);
            }

            pascal.add(Arrays.asList(curRow));
        }
        return pascal;
    }

    public static void main(String[] args) {
        PascalTriangle_118 pascal = new PascalTriangle_118();
        int i = 5;
        for (List<Integer> row : pascal.generate(i))
            System.out.println(row);
    }
}
