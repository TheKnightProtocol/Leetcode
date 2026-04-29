import java.util.Arrays;

class Solution {
    public long maximumScore(int[][] grid) {
        int n = grid.length;
        if (n == 1) return 0;

        long[][] pref = new long[n][n + 1];
        for (int j = 0; j < n; j++) {
            pref[j][0] = 0;
            for (int i = 1; i <= n; i++) {
                pref[j][i] = pref[j][i - 1] + grid[i - 1][j];
            }
        }

        int H = n + 1;
        long[][] dp = new long[H][H];
        for (int i = 0; i < H; i++) Arrays.fill(dp[i], Long.MIN_VALUE / 2);
        for (int c = 0; c < H; c++) dp[0][c] = 0;

        for (int j = 0; j < n - 1; j++) {
            long[][] ndp = new long[H][H];
            for (int i = 0; i < H; i++) Arrays.fill(ndp[i], Long.MIN_VALUE / 2);
            for (int prev = 0; prev < H; prev++) {
                for (int curr = 0; curr < H; curr++) {
                    long curVal = dp[prev][curr];
                    if (curVal == Long.MIN_VALUE / 2) continue;
                    for (int nxt = 0; nxt < H; nxt++) {
                        int m = Math.max(prev, nxt);
                        long add = 0;
                        if (m > curr) {
                            add = pref[j][m] - pref[j][curr];
                        }
                        long val = curVal + add;
                        if (val > ndp[curr][nxt]) ndp[curr][nxt] = val;
                    }
                }
            }
            dp = ndp;
        }

        long ans = 0;
        for (int prev = 0; prev < H; prev++) {
            for (int curr = 0; curr < H; curr++) {
                long curVal = dp[prev][curr];
                if (curVal == Long.MIN_VALUE / 2) continue;
                int m = Math.max(prev, 0);
                long add = 0;
                if (m > curr) {
                    add = pref[n - 1][m] - pref[n - 1][curr];
                }
                long total = curVal + add;
                if (total > ans) ans = total;
            }
        }
        return ans;
    }
}
