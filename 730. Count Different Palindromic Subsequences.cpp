class Solution {
private:
    const int MOD = 1e9 + 7;
    
public:
    int countPalindromicSubsequences(string s) {
        int n = s.length();
        vector<vector<long long>> dp(n, vector<long long>(n, 0));
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                
                if (s[i] != s[j]) {
                    dp[i][j] = (dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1] + MOD) % MOD;
                } else {
                    dp[i][j] = (2 * dp[i+1][j-1]) % MOD;
                    
                    int left = i + 1;
                    int right = j - 1;
                    
                    while (left <= right && s[left] != s[i]) {
                        left++;
                    }
                    while (left <= right && s[right] != s[i]) {
                        right--;
                    }
                    
                    if (left > right) {
                        dp[i][j] = (dp[i][j] + 2) % MOD;
                    } else if (left == right) {
                        dp[i][j] = (dp[i][j] + 1) % MOD;
                    } else {
                        dp[i][j] = (dp[i][j] - dp[left+1][right-1] + MOD) % MOD;
                    }
                }
            }
        }
        
        return dp[0][n-1];
    }
};
