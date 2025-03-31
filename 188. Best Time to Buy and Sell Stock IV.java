class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        // If k is large enough, use a greedy approach
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

        // DP table: transactions (rows) × days (columns)
        int[][] dp = new int[k + 1][n];

        // Fill the DP table
        for (int t = 1; t <= k; t++) {
            int maxDiff = -prices[0]; // Max profit after buying
            for (int d = 1; d < n; d++) {
                dp[t][d] = Math.max(dp[t][d - 1], prices[d] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[t - 1][d] - prices[d]);
            }
        }
        return dp[k][n - 1]; // Maximum profit with at most k transactions
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.maxProfit(2, new int[]{2, 4, 1}));       // Output: 2
        System.out.println(sol.maxProfit(2, new int[]{3, 2, 6, 5, 0, 3})); // Output: 7
    }
}
