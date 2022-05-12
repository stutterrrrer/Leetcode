import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands_200 {
    private char[][] grid;
    private int rows, cols;

    public int numIslandsBFS(char[][] grid) {
        initializeInstanceVariables(grid);
        int count = 0;
        Queue<Integer> bfs = new LinkedList<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != '1') continue;
                count++;
                visitAndEnqueue(i, j, bfs);
                while (!bfs.isEmpty()) {
                    int pos = bfs.poll();
                    int r = pos / cols, c = pos % cols;
                    if (r > 0) visitAndEnqueue(r - 1, c, bfs);
                    if (r < rows - 1) visitAndEnqueue(r + 1, c, bfs);
                    if (c > 0) visitAndEnqueue(r, c - 1, bfs);
                    if (c < cols - 1) visitAndEnqueue(r, c + 1, bfs);
                }
            }
        return count;
    }

    private void visitAndEnqueue(int r, int c, Queue<Integer> bfs) {
        if (grid[r][c] != '1') return; // only enqueue if unvisited - avoid pile-up
        grid[r][c] = '2'; // !! mark visited - avoid enqueueing duplicates
        bfs.offer(r * cols + c);
    }

    private void initializeInstanceVariables(char[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    public int numIslands(char[][] grid) {
        initializeInstanceVariables(grid);
        int count = 0;
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (grid[r][c] == '1') {
                    dfs(r, c);
                    count++;
                }
        return count;
    }

    private void dfs(int r, int c) {
        if (grid[r][c] != '1') return;
        grid[r][c] = '2';
        if (r > 0) dfs(r - 1, c);
        if (r < rows - 1) dfs(r + 1, c);
        if (c > 0) dfs(r, c - 1);
        if (c < cols - 1) dfs(r, c + 1);
    }

    public static void main(String[] args) {
        char[][] grid =
                {
                        {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1' },
                        {'0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0' },
                        {'1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
                        {'1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
                        {'1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' }, {
                        '1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1' }, {
                        '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1' }, {
                        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1' }, {
                        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1' }, {
                        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' }, {
                        '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' }, {
                        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' }, {
                        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' }, {
                        '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1' }, {
                        '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1' }, {
                        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0' }, {
                        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0' }, {
                        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' }, {
                        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' }, {
                        '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' }};
        System.out.println(new NumberOfIslands_200().numIslandsBFS(grid));
    }
}
