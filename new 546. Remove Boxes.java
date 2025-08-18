class Solution {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return dfs(boxes, 0, n - 1, 0, dp);
    }

    private int dfs(int[] boxes, int l, int r, int k, int[][][] dp) {
        if (l > r) return 0;
        if (dp[l][r][k] != 0) return dp[l][r][k];
        while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        dp[l][r][k] = dfs(boxes, l, r - 1, 0, dp) + (k + 1) * (k + 1);
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k], dfs(boxes, l, i, k + 1, dp) + dfs(boxes, i + 1, r - 1, 0, dp));
            }
        }
        return dp[l][r][k];
    }
}
