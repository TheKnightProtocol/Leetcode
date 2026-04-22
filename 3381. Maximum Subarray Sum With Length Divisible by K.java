class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        Long[] minPrefix = new Long[k];
        long maxSum = Long.MIN_VALUE;
        
        for (int i = 0; i <= n; i++) {
            int remainder = i % k;
            
            if (minPrefix[remainder] != null) {
                long currentSum = prefixSum[i] - minPrefix[remainder];
                maxSum = Math.max(maxSum, currentSum);
            }
            
            if (minPrefix[remainder] == null || prefixSum[i] < minPrefix[remainder]) {
                minPrefix[remainder] = prefixSum[i];
            }
        }
        
        return maxSum;
    }
}
