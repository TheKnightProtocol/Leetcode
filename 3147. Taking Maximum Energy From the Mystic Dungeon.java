class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n];
        int maxEnergy = Integer.MIN_VALUE;
        
        // Fill dp array from right to left
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = energy[i];
            
            // If we can jump to a valid position, add its dp value
            if (i + k < n) {
                dp[i] += dp[i + k];
            }
            
            // Update maximum energy
            if (dp[i] > maxEnergy) {
                maxEnergy = dp[i];
            }
        }
        
        return maxEnergy;
    }
}
