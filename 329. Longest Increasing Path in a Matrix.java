public class Solution {
    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;

        int[][] dp = new int[m][n]; // Memoization table
        int maxPath = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j, dp));
            }
        }

        return maxPath;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] dp) {
        if (dp[i][j] != 0) return dp[i][j]; // Return cached result

        int max = 1;
        for (int[] dir : directions) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] > matrix[i][j]) {
                max = Math.max(max, 1 + dfs(matrix, x, y, dp));
            }
        }

        dp[i][j] = max;
        return max;
    }
}
