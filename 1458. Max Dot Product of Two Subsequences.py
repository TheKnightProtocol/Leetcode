class Solution:
    def maxDotProduct(self, nums1, nums2):
        n, m = len(nums1), len(nums2)
        dp = [[-float('inf')] * (m + 1) for _ in range(n + 1)]
        
        for i in range(1, n + 1):
            for j in range(1, m + 1):
                prod = nums1[i - 1] * nums2[j - 1]
                # start new subsequence with this pair
                dp[i][j] = max(dp[i][j], prod)
                # add this pair to previous subsequence
                if dp[i - 1][j - 1] != -float('inf'):
                    dp[i][j] = max(dp[i][j], dp[i - 1][j - 1] + prod)
                # skip nums1[i-1] or nums2[j-1]
                dp[i][j] = max(dp[i][j], dp[i - 1][j], dp[i][j - 1])
        
        return dp[n][m]
