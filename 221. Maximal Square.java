class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1]; // Extra row & column for easy indexing
        int maxLen = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }

        return maxLen * maxLen; // Return the area of the largest square
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        char[][] matrix1 = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        System.out.println(sol.maximalSquare(matrix1)); // Output: 4

        char[][] matrix2 = {
            {'0', '1'},
            {'1', '0'}
        };
        System.out.println(sol.maximalSquare(matrix2)); // Output: 1

        char[][] matrix3 = {
            {'0'}
        };
        System.out.println(sol.maximalSquare(matrix3)); // Output: 0
    }
}
