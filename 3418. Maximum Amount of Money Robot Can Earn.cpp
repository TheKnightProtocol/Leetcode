class Solution {
public:
    int maximumAmount(vector<vector<int>>& coins) {
        int m = coins.size();
        int n = coins[0].size();
        
        vector<vector<vector<int>>> dp(m, vector<vector<int>>(n, vector<int>(3, INT_MIN)));
        
        dp[0][0][0] = coins[0][0];
        if (coins[0][0] < 0) {
            dp[0][0][1] = 0;
        } else {
            dp[0][0][1] = coins[0][0];
            dp[0][0][2] = coins[0][0];
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k <= 2; k++) {
                    if (dp[i][j][k] == INT_MIN) continue;
                    
                    if (j + 1 < n) {
                        dp[i][j+1][k] = max(dp[i][j+1][k], dp[i][j][k] + coins[i][j+1]);
                        
                        if (coins[i][j+1] < 0 && k < 2) {
                            dp[i][j+1][k+1] = max(dp[i][j+1][k+1], dp[i][j][k]);
                        }
                    }
                    
                    if (i + 1 < m) {
                        dp[i+1][j][k] = max(dp[i+1][j][k], dp[i][j][k] + coins[i+1][j]);
                        
                        if (coins[i+1][j] < 0 && k < 2) {
                            dp[i+1][j][k+1] = max(dp[i+1][j][k+1], dp[i][j][k]);
                        }
                    }
                }
            }
        }
        
        return max({dp[m-1][n-1][0], dp[m-1][n-1][1], dp[m-1][n-1][2]});
    }
};
