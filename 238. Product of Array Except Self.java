class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Step 1: Calculate prefix product for each index
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // Step 2: Multiply with suffix product (right-to-left)
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] *= right;
            right *= nums[i];
        }

        return answer;
    }
}
