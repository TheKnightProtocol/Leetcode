class Solution {
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int max = res, min = res;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }

            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);

            res = Math.max(res, max);
        }

        return res;
    }
}
