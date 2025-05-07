public class Solution {
    public int checkRecord(int n) {
        int MOD = 1000000007;

        // Step 1: DP for strings with no 'A' and no "LLL"
        long[] dp = new long[n + 3];
        dp[0] = 1; // empty string
        dp[1] = 2; // "P", "L"
        dp[2] = 4; // "PP", "PL", "LP", "LL"

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
        }

        // Step 2: Total = valid strings with 0 'A' + valid strings with exactly 1 'A'
        long total = dp[n]; // no 'A'

        // Insert one 'A' at every possible position
        for (int i = 0; i < n; i++) {
            long left = dp[i];           // valid string before 'A'
            long right = dp[n - 1 - i];  // valid string after 'A'
            total = (total + (left * right) % MOD) % MOD;
        }

        return (int) total;
    }
}
