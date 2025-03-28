class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        // DP table to store the minimum health required at each room
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize dp with a large number to avoid negative health values
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        // The bottom-right corner (princess's position) must have at least 1 health
        dp[m][n - 1] = 1;
        dp[m - 1][n] = 1;
        
        // Fill the dp table from the bottom-right to the top-left
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Calculate the minimum health required at the current room
                int minHealthNextStep = Math.min(dp[i + 1][j], dp[i][j + 1]);
                int requiredHealth = minHealthNextStep - dungeon[i][j];
                
                // If the required health is less than or equal to 0, set it to 1 (the knight must have at least 1 health)
                dp[i][j] = Math.max(1, requiredHealth);
            }
        }
        
        // The answer is the minimum health required to start at the top-left corner
        return dp[0][0];
    }
}
