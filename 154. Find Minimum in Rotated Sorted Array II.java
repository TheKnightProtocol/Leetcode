public class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else { 
                // nums[mid] == nums[right], cannot decide, reduce right
                right--;
            }
        }
        return nums[left]; // or nums[right] since left == right
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMin(new int[]{1, 3, 5})); // Output: 1
        System.out.println(solution.findMin(new int[]{2, 2, 2, 0, 1})); // Output: 0
        System.out.println(solution.findMin(new int[]{10, 10, 10, 1, 10})); // Output: 1
    }
}
