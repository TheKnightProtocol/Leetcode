public class Solution {
    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int totalSum = 0;
        // Iterate through all possible subsets (2^n combinations)
        for (int i = 0; i < (1 << n); i++) {
            int xorSum = 0;
            // Calculate XOR for each subset
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    xorSum ^= nums[j];
                }
            }
            totalSum += xorSum;
        }
        return totalSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 3};
        System.out.println(solution.subsetXORSum(nums1)); // Output: 6
        
        int[] nums2 = {5, 1, 6};
        System.out.println(solution.subsetXORSum(nums2)); // Output: 28
        
        int[] nums3 = {3, 4, 5, 6, 7, 8};
        System.out.println(solution.subsetXORSum(nums3)); // Output: 480
    }
}
