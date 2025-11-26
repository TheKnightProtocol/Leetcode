class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1000000007;
        
        int[][][] dp = new int[m][n][k];
        dp[0][0][grid[0][0] % k] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int r = 0; r < k; r++) {
                    if (dp[i][j][r] > 0) {
                        if (i + 1 < m) {
                            int newR = (r + grid[i + 1][j]) % k;
                            dp[i + 1][j][newR] = (dp[i + 1][j][newR] + dp[i][j][r]) % MOD;
                        }
                        if (j + 1 < n) {
                            int newR = (r + grid[i][j + 1]) % k;
                            dp[i][j + 1][newR] = (dp[i][j + 1][newR] + dp[i][j][r]) % MOD;
                        }
                    }
                }
            }
        }
        
        return dp[m - 1][n - 1][0];
    }
}
