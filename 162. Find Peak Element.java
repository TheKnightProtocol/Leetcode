class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[mid + 1]) {
                // Peak is on the left side (including mid)
                right = mid;
            } else {
                // Peak is on the right side
                left = mid + 1;
            }
        }

        return left; // 'left' or 'right' will point to the peak
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] nums1 = {1,2,3,1};
        System.out.println(sol.findPeakElement(nums1)); // Output: 2

        int[] nums2 = {1,2,1,3,5,6,4};
        System.out.println(sol.findPeakElement(nums2)); // Output: 1 or 5
    }
}
