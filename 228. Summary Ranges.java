import java.util.*;

class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        if (nums.length == 0) return result;

        int start = nums[0]; // Start of a new range

        for (int i = 1; i <= nums.length; i++) {
            // Check end of range (either end of array or gap found)
            if (i == nums.length || nums[i] != nums[i - 1] + 1) {
                if (start == nums[i - 1]) {
                    result.add(start + ""); // Single number
                } else {
                    result.add(start + "->" + nums[i - 1]); // Range
                }

                // If not end of array, update start
                if (i < nums.length) {
                    start = nums[i];
                }
            }
        }

        return result;
    }

    // For testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.summaryRanges(new int[]{0,1,2,4,5,7}));
        // Output: ["0->2", "4->5", "7"]

        System.out.println(sol.summaryRanges(new int[]{0,2,3,4,6,8,9}));
        // Output: ["0", "2->4", "6", "8->9"]

        System.out.println(sol.summaryRanges(new int[]{}));
        // Output: []

        System.out.println(sol.summaryRanges(new int[]{1}));
        // Output: ["1"]
    }
}
