class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;

        int m = board.length, n = board[0].length;

        // Step 1: Mark all border 'O's and their connected 'O's as safe ('T')
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') dfs(board, i, 0);
            if (board[i][n - 1] == 'O') dfs(board, i, n - 1);
        }
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') dfs(board, 0, j);
            if (board[m - 1][j] == 'O') dfs(board, m - 1, j);
        }

        // Step 2: Flip remaining 'O' to 'X', and convert 'T' back to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';  // Captured region
                } else if (board[i][j] == 'T') {
                    board[i][j] = 'O';  // Restore safe regions
                }
            }
        }
    }

    // DFS to mark safe 'O's connected to the border
    private void dfs(char[][] board, int r, int c) {
        int m = board.length, n = board[0].length;
        if (r < 0 || c < 0 || r >= m || c >= n || board[r][c] != 'O') return;
        
        board[r][c] = 'T';  // Mark as safe

        // Explore in all 4 directions
        dfs(board, r + 1, c);
        dfs(board, r - 1, c);
        dfs(board, r, c + 1);
        dfs(board, r, c - 1);
    }
}
