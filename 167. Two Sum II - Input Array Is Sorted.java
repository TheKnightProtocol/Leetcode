class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1}; // Convert to 1-based index
            } else if (sum < target) {
                left++;  // Increase sum by moving left pointer
            } else {
                right--; // Decrease sum by moving right pointer
            }
        }
        return new int[]{-1, -1}; // This case never occurs as per problem constraints
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] result1 = sol.twoSum(new int[]{2,7,11,15}, 9);
        int[] result2 = sol.twoSum(new int[]{2,3,4}, 6);
        int[] result3 = sol.twoSum(new int[]{-1,0}, -1);

        System.out.println(Arrays.toString(result1)); // Output: [1,2]
        System.out.println(Arrays.toString(result2)); // Output: [1,3]
        System.out.println(Arrays.toString(result3)); // Output: [1,2]
    }
}
