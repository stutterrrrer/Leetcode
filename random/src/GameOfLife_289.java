public class GameOfLife_289 {
    private static final int[][] OFFSETS = {{0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}, {-1, -1}, {-1, 0}, {-1, 1}};

    public void gameOfLifeSimple(int[][] board) {
        int height = board.length, width = board[0].length;
        int[][] copy = new int[height][width];
        for (int i = 0; i < height; i++) copy[i] = board[i].clone();
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int liveCount = copy[row][col]; // count itself in; the total # of "1" in a 9-cell grid.
                for (int[] offset : OFFSETS) {
                    int r = row + offset[0], c = col + offset[1];
                    if (r >= 0 && r < height && c >= 0 && c < width && copy[r][c] == 1) liveCount++;
                }
                if (liveCount < 3 || liveCount > 4) board[row][col] = 0;
                else if (liveCount == 3) board[row][col] = 1;
                else board[row][col] = copy[row][col]; // when live count = 4
            }
        }
    }

    public void gameOfLife(int[][] board) {
        int height = board.length, width = board[0].length;
        // temporary states: changes: (0 -> 1: -1) & (1 -> 0: 2); no changes: (1 -> 1: 1) & (0 -> 0: 0)
        // such that all cells that were originally 1 are positive, and all cells afterwards will be (cell % 2)
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int liveNeighbors = 0;
                for (int[] offset : OFFSETS) {
                    int r = row + offset[0], c = col + offset[1];
                    if (r >= 0 && r < height && c >= 0 && c < width && board[r][c] > 0) liveNeighbors++;
                }
                if (board[row][col] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) board[row][col] = 2;
                else if (board[row][col] == 0 && liveNeighbors == 3) board[row][col] = -1;
            }
        }
        for (int[] row : board)
            for (int col = 0; col < width; col++)
                row[col] = Math.abs(row[col] % 2);
    }
}
