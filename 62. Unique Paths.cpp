// C++
class Solution {
public:
    int uniquePaths(int m, int n) {
        // Create a 2D DP table to store the number of paths to each cell
        // dp[i][j] will store the number of unique paths to reach cell (i, j)
        std::vector<std::vector<int>> dp(m, std::vector<int>(n, 0));

        // Initialize the first row and first column
        // There's only one way to reach any cell in the first row (by moving right)
        for (int j = 0; j < n; ++j) {
            dp[0][j] = 1;
        }
        // There's only one way to reach any cell in the first column (by moving down)
        for (int i = 0; i < m; ++i) {
            dp[i][0] = 1;
        }

        // Fill the rest of the DP table
        // For any cell (i, j), the robot can only come from (i-1, j) or (i, j-1)
        // So, dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        // The result is the number of paths to the bottom-right corner
        return dp[m-1][n-1];
    }
};
