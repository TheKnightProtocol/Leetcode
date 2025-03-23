import java.util.HashSet;

class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num); // Store all numbers in a set

        int maxLength = 0;

        for (int num : numSet) {
            // Start counting only if it's the beginning of a sequence
            if (!numSet.contains(num - 1)) { 
                int currentNum = num;
                int currentLength = 1;

                // Expand the sequence
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }

                maxLength = Math.max(maxLength, currentLength);
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println(solution.longestConsecutive(nums1)); // Output: 4

        int[] nums2 = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(solution.longestConsecutive(nums2)); // Output: 9

        int[] nums3 = {1, 0, 1, 2};
        System.out.println(solution.longestConsecutive(nums3)); // Output: 3
    }
}
