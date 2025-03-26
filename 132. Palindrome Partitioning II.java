import java.util.Arrays;

public class Solution {  // Rename class to "Solution"
    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) return 0;
        
        // Step 1: Precompute palindrome substrings
        boolean[][] isPalindrome = new boolean[n][n];
        for (int len = 1; len <= n; len++) { 
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len <= 2) isPalindrome[i][j] = true;
                    else isPalindrome[i][j] = isPalindrome[i + 1][j - 1];
                }
            }
        }
        
        // Step 2: DP to find minimum cuts
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            if (isPalindrome[0][i]) {
                dp[i] = 0;  // No cut needed if the whole prefix is a palindrome
            } else {
                for (int j = 0; j < i; j++) {
                    if (isPalindrome[j + 1][i]) {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minCut("aab"));  // Output: 1
        System.out.println(solution.minCut("a"));    // Output: 0
        System.out.println(solution.minCut("ab"));   // Output: 1
    }
}
