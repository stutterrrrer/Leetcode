public class WordSearchGrid_79 {
    private char[][] board;
    private boolean[][] visited;
    String word;
    int height, width;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        this.board = board;
        height = board.length;
        width = board[0].length;
        this.word = word;
        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++)
                if (searchWordStarting(0, row, col)) return true;
        return false;
    }

    private boolean searchWordStarting(int bgn, int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width || board[row][col] != word.charAt(bgn)) return false;
        if (bgn == word.length() - 1) return true;
        board[row][col] = '#'; // mark as visited - clean up when returning
        for (int[] offset : directions) {
            if (searchWordStarting(bgn + 1, row + offset[0], col + offset[1])) {
                board[row][col] = word.charAt(bgn); // clean up - restore input
                return true; // return true immediately, before visiting other neighbors
            }
        }
        board[row][col] = word.charAt(bgn); // backtrack
        return false;
    }

    private boolean wordSearchStartingNaive(int bgn, int row, int col) {
        if (visited[row][col] || board[row][col] != word.charAt(bgn)) return false;
        if (bgn == word.length() - 1) return true;
        visited[row][col] = true;
        boolean found = false;
        if (row > 0) found = found || wordSearchStartingNaive(bgn + 1, row - 1, col);
        if (row < height - 1) found = found || wordSearchStartingNaive(bgn + 1, row + 1, col);
        if (col > 0) found = found || wordSearchStartingNaive(bgn + 1, row, col - 1);
        if (col < width - 1) found = found || wordSearchStartingNaive(bgn + 1, row, col + 1);
        visited[row][col] = false;
        return found;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        WordSearchGrid_79 solver = new WordSearchGrid_79();
        System.out.println(solver.exist(board, word));
    }
}
