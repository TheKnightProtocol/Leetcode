class Solution {
    public int numDecodings(String s) {
        int MOD = 1000000007;
        int n = s.length();
        long dp0 = 1;  // ways to decode empty prefix
        long dp1 = 0;  // ways to decode first i-1 chars

        // Initialize dp1
        char c0 = s.charAt(0);
        if (c0 == '*') dp1 = 9;
        else if (c0 != '0') dp1 = 1;
        else dp1 = 0;

        for (int i = 1; i < n; i++) {
            char c1 = s.charAt(i - 1);
            char c2 = s.charAt(i);
            long dp2 = 0;

            // Single character decoding
            if (c2 == '*') dp2 += 9 * dp1;
            else if (c2 != '0') dp2 += dp1;

            // Two-character decoding
            if (c1 == '*') {
                if (c2 == '*') {
                    dp2 += 15 * dp0;  // 11-19 and 21-26
                } else if (c2 >= '0' && c2 <= '6') {
                    dp2 += 2 * dp0;  // 1x and 2x
                } else {
                    dp2 += 1 * dp0;  // 1x only
                }
            } else if (c1 == '1') {
                if (c2 == '*') dp2 += 9 * dp0;  // 11-19
                else dp2 += dp0;               // 10-19
            } else if (c1 == '2') {
                if (c2 == '*') dp2 += 6 * dp0;  // 21-26
                else if (c2 >= '0' && c2 <= '6') dp2 += dp0;  // 20-26
            }

            dp2 %= MOD;
            dp0 = dp1;
            dp1 = dp2;
        }

        return (int) dp1;
    }
}
