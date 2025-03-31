class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Handle cases where k >= n

        for (int i = 0; i < k; i++) {
            int last = nums[n - 1];  // Store the last element
            for (int j = n - 1; j > 0; j--) {
                nums[j] = nums[j - 1]; // Shift elements right
            }
            nums[0] = last; // Place the last element at the beginning
        }
    }
}
