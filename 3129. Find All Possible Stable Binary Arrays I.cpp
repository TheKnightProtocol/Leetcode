class Solution {
public:
    int numberOfStableArrays(int zero, int one, int limit) {
        const int MOD = 1e9 + 7;
        
        // dp[z][o][last] = number of ways with z zeros, o ones, ending with 'last'
        vector<vector<vector<long long>>> dp(
            zero + 1, 
            vector<vector<long long>>(one + 1, vector<long long>(2, 0))
        );
        
        for (int z = 0; z <= zero; z++) {
            for (int o = 0; o <= one; o++) {
                if (z == 0 && o == 0) continue;
                
                // Try ending with 0
                if (z > 0) {
                    // If we end with 0, we can have 1 to limit consecutive zeros at the end
                    for (int k = 1; k <= limit; k++) {
                        if (z - k >= 0) {
                            if (z - k == 0 && o == 0) {
                                // All zeros case
                                if (k <= limit) dp[z][o][0] = (dp[z][o][0] + 1) % MOD;
                            } else {
                                // Add from sequences ending with 1
                                dp[z][o][0] = (dp[z][o][0] + dp[z - k][o][1]) % MOD;
                            }
                        }
                    }
                }
                
                // Try ending with 1
                if (o > 0) {
                    // If we end with 1, we can have 1 to limit consecutive ones at the end
                    for (int k = 1; k <= limit; k++) {
                        if (o - k >= 0) {
                            if (o - k == 0 && z == 0) {
                                // All ones case
                                if (k <= limit) dp[z][o][1] = (dp[z][o][1] + 1) % MOD;
                            } else {
                                // Add from sequences ending with 0
                                dp[z][o][1] = (dp[z][o][1] + dp[z][o - k][0]) % MOD;
                            }
                        }
                    }
                }
            }
        }
        
        return (dp[zero][one][0] + dp[zero][one][1]) % MOD;
    }
};
