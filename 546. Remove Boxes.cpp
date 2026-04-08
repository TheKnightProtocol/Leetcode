class Solution {
public:
    int removeBoxes(vector<int>& boxes) {
        int n = boxes.size();
        int dp[100][100][100] = {0};
        
        for (int i = 0; i < n; i++) {
            for (int k = 0; k <= i; k++) {
                dp[i][i][k] = (k + 1) * (k + 1);
            }
        }
        
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len <= n; l++) {
                int r = l + len - 1;
                for (int k = 0; k <= l; k++) {
                    // Option 1: Remove boxes[l] with its k same-colored friends
                    int res = (k + 1) * (k + 1) + dp[l + 1][r][0];
                    
                    // Option 2: Merge with a later same-colored box
                    for (int m = l + 1; m <= r; m++) {
                        if (boxes[m] == boxes[l]) {
                            res = max(res, dp[l + 1][m - 1][0] + dp[m][r][k + 1]);
                        }
                    }
                    dp[l][r][k] = res;
                }
            }
        }
        
        return dp[0][n - 1][0];
    }
};
