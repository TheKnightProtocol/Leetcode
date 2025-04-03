class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0]; // Only one house to rob
        if (n == 2) return Math.max(nums[0], nums[1]); // Pick the max of two

        // Compute the maximum money excluding the first house and excluding the last house
        return Math.max(robLinear(nums, 0, n - 2), robLinear(nums, 1, n - 1));
    }

    private int robLinear(int[] nums, int start, int end) {
        int prev1 = 0, prev2 = 0;  // Initialize variables for DP
        for (int i = start; i <= end; i++) {
            int temp = Math.max(prev1, prev2 + nums[i]);  // Current max rob amount
            prev2 = prev1; // Shift values for next iteration
            prev1 = temp;
        }
        return prev1; // Final maximum amount
    }
}
