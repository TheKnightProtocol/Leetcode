class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        
        long sum = 0;
        long f = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f += (long) i * nums[i];
        }
        
        long max = f;
        for (int k = 1; k < n; k++) {
            f = f + sum - (long) n * nums[n - k];
            if (f > max) max = f;
        }
        return (int) max;
    }
}
