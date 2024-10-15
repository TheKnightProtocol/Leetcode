public class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // Create a DP table
        int[][] dp = new int[m + 1][n + 1];
        
        // Initialize the base case
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1; // Empty t can be formed in one way
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // If characters match
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; // Use or ignore the character
                } else {
                    dp[i][j] = dp[i - 1][j]; // Ignore the character
                }
            }
        }
        
        // The answer is in the last cell
        return dp[m][n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // Test cases
        System.out.println(solution.numDistinct("rabbbit", "rabbit")); // Output: 3
        System.out.println(solution.numDistinct("babgbag", "bag"));    // Output: 5
    }
}
