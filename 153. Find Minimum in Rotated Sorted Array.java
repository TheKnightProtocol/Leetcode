public class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left]; // or nums[right], since left == right
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMin(new int[]{3, 4, 5, 1, 2})); // Output: 1
        System.out.println(solution.findMin(new int[]{4, 5, 6, 7, 0, 1, 2})); // Output: 0
        System.out.println(solution.findMin(new int[]{11, 13, 15, 17})); // Output: 11
    }
}
