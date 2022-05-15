import java.util.LinkedList;
import java.util.Queue;

public class BinaryMatrixShortestPath_1091 {
    private final int[][] eightDirections = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private int[][] matrix;
    private int m;

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        boolean[][] visited = new boolean[n][n];
        Queue<Integer> curLevel = new LinkedList<>();
        curLevel.offer(0);
        visited[0][0] = true;
        int steps = 1;
        while (!curLevel.isEmpty() && !visited[n - 1][n - 1]) {
            Queue<Integer> nextLevel = new LinkedList<>();
            while (!curLevel.isEmpty()) {
                int position = curLevel.poll(), r = position / n, c = position % n;
                for (int[] offset : eightDirections) {
                    int row = r + offset[0], col = c + offset[1];
                    if (row >= 0 && row < n && col >= 0 && col < n && grid[row][col] == 0 && !visited[row][col]) {
                        visited[row][col] = true;
                        nextLevel.offer(row * n + col);
                    }
                }
            }
            steps++;
            curLevel = nextLevel;
        }
        return visited[n - 1][n - 1] ? steps : -1;
    }

    public int shortestPathBinaryMatrixStepsQueued(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> rowColSteps = new LinkedList<>();
        rowColSteps.offer(new int[]{0, 0, 1});
        visited[0][0] = true;
        int shortestDistance = 1;
        while (!rowColSteps.isEmpty() && !visited[n - 1][n - 1]) {
            int[] cur = rowColSteps.poll();
            int r = cur[0], c = cur[1], steps = cur[2];
            for (int[] offset : eightDirections) {
                int row = r + offset[0], col = c + offset[1];
                if (row >= 0 && row < n && col >= 0 && col < n && grid[row][col] == 0 && !visited[row][col]) {
                    rowColSteps.offer(new int[]{row, col, steps + 1});
                    visited[row][col] = true;
                    if (row == n - 1 && col == n - 1) shortestDistance = steps + 1;
                }
            }
        }
        return visited[n - 1][n - 1] ? shortestDistance : -1;
    }

    public int shortestPathBinaryMatrixModifyInput(int[][] grid) {
        this.matrix = grid;
        m = grid.length;
        Queue<Integer> bfs = new LinkedList<>();
        if (grid[0][0] == 1 || grid[m - 1][m - 1] == 1) return -1;
        // mark the traversed cells with the # of steps it took to get there
        visit(0, 0, 1, bfs);
        while (!bfs.isEmpty() && grid[m - 1][m - 1] == 0) {
            int position = bfs.poll(), row = position / m, col = position % m;
            for (int[] offsets : eightDirections)
                visit(row + offsets[0], col + offsets[1], grid[row][col] + 1, bfs);
        }
        return grid[m - 1][m - 1] == 0 ? -1 : grid[m - 1][m - 1];
    }

    private void visit(int row, int col, int steps, Queue<Integer> bfs) {
        if (row >= 0 && row < m && col >= 0 && col < m
                && matrix[row][col] == 0) {
            matrix[row][col] = steps;
            bfs.offer(row * m + col);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println(new BinaryMatrixShortestPath_1091().shortestPathBinaryMatrix(grid));
    }
}
