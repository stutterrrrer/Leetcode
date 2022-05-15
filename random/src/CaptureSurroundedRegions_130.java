public class CaptureSurroundedRegions_130 {
    private char[][] board;
    private int height, width;

    public void solve(char[][] board) {
        this.board = board;
        height = board.length;
        width = board[0].length;
        for (int col = 0; col < width; col++) {
            dfs(0, col); // top border
            dfs(height - 1, col); // bottom border
        }
        for (int row = 0; row < height; row++) {
            dfs(row, 0); // left border
            dfs(row, width - 1); // right border
        }
        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++)
                board[row][col] = board[row][col] == 'E' ? 'O' : 'X';
    }

    private void dfs(int row, int col) {
        if (board[row][col] != 'O' || board[row][col] == 'E') return;
        board[row][col] = 'E'; // 'E' as in "Escaped" because it's connected to a border
        if (row > 0) dfs(row - 1, col);
        if (row < height - 1) dfs(row + 1, col);
        if (col > 0) dfs(row, col - 1);
        if (col < width - 1) dfs(row, col + 1);
    }
}