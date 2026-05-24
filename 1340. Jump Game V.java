import java.util.*;

class Solution {
    int[] dp;
    int[] arr;
    int d;
    int n;

    public int maxJumps(int[] arr, int d) {
        this.arr = arr;
        this.d = d;
        this.n = arr.length;
        dp = new int[n];
        Arrays.fill(dp, -1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i));
        }
        return ans;
    }

    private int dfs(int i) {
        if (dp[i] != -1) return dp[i];
        int best = 1;
        // left jumps
        for (int j = i - 1; j >= Math.max(0, i - d); j--) {
            if (arr[j] >= arr[i]) break;
            best = Math.max(best, 1 + dfs(j));
        }
        // right jumps
        for (int j = i + 1; j <= Math.min(n - 1, i + d); j++) {
            if (arr[j] >= arr[i]) break;
            best = Math.max(best, 1 + dfs(j));
        }
        dp[i] = best;
        return best;
    }
}
