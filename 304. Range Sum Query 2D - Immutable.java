class NumMatrix {
    int[][] dp;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        dp = new int[m + 1][n + 1];
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                dp[r + 1][c + 1] = matrix[r][c] + dp[r][c + 1] + dp[r + 1][c] - dp[r][c];
    }

    public int sumRegion(int r1, int c1, int r2, int c2) {
        return dp[r2 + 1][c2 + 1] - dp[r1][c2 + 1] - dp[r2 + 1][c1] + dp[r1][c1];
    }
}
