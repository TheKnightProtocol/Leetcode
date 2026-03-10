#include <vector>
using namespace std;

class Solution {
public:
    int numberOfStableArrays(int zero, int one, int limit) {
        long long MOD = 1e9 + 7;
        // dp[i][j][0] = stable arrays with i zeros, j ones, ending in 0
        // dp[i][j][1] = stable arrays with i zeros, j ones, ending in 1
        vector<vector<vector<long long>>> dp(zero + 1, vector<vector<long long>>(one + 1, vector<long long>(2, 0)));

        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                if (i == 0 && j == 0) continue;

                // Filling ending with 0
                if (i > 0) {
                    if (i == 1 && j == 0) dp[i][j][0] = 1; // Base case: "0"
                    else {
                        long long val = (dp[i - 1][j][0] + dp[i - 1][j][1]) % MOD;
                        if (i > limit) {
                            // Subtract the case where we just added a (limit+1)-th consecutive 0
                            // That happens if the sequence was: [ending in 1 at i-limit-1] + [limit 0s]
                            // and we just added one more 0.
                            val = (val - (i - limit - 1 >= 0 ? dp[i - limit - 1][j][1] : 0) + MOD) % MOD;
                            // Special case: if we are at exactly limit + 1 zeros and 0 ones, 
                            // the sequence "00...0" becomes invalid.
                            if (i == limit + 1 && j == 0) val = (val - 1 + MOD) % MOD;
                        }
                        dp[i][j][0] = val;
                    }
                }

                // Filling ending with 1
                if (j > 0) {
                    if (j == 1 && i == 0) dp[i][j][1] = 1; // Base case: "1"
                    else {
                        long long val = (dp[i][j - 1][0] + dp[i][j - 1][1]) % MOD;
                        if (j > limit) {
                            val = (val - (j - limit - 1 >= 0 ? dp[i][j - limit - 1][0] : 0) + MOD) % MOD;
                            if (j == limit + 1 && i == 0) val = (val - 1 + MOD) % MOD;
                        }
                        dp[i][j][1] = val;
                    }
                }
            }
        }

        return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
    }
};
