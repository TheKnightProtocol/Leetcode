public class Solution {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        Integer[][][] dp = new Integer[n][n][n];

        return dfs(boxes, 0, n - 1, 0, dp);
    }

    private int dfs(int[] boxes, int l, int r, int k, Integer[][][] dp) {
        if (l > r) return 0;
        if (dp[l][r][k] != null) return dp[l][r][k];

        while (l < r && boxes[l] == boxes[l + 1]) {
            l++;
            k++;
        }

        int res = (k + 1) * (k + 1) + dfs(boxes, l + 1, r, 0, dp);

        for (int i = l + 1; i <= r; i++) {
            if (boxes[i] == boxes[l]) {
                res = Math.max(res, dfs(boxes, l + 1, i - 1, 0, dp) + dfs(boxes, i, r, k + 1, dp));
            }
        }

        dp[l][r][k] = res;
        return res;
    }
}
