class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        if (n == 0) return 0;

        // Remove consecutive duplicate characters
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                sb.append(s.charAt(i));
            }
        }
        s = sb.toString();
        n = s.length();

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int l = 2; l <= n; l++) { // length of substring
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int turns = dp[i][k] + dp[k + 1][j];
                    if (s.charAt(k) == s.charAt(j)) {
                        turns--;
                    }
                    dp[i][j] = Math.min(dp[i][j], turns);
                }
            }
        }

        return dp[0][n - 1];
    }
}
