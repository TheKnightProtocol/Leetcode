import java.util.Arrays;

class Solution {
    public int triangleNumber(int[] nums) {
        // Sort the array to easily apply the triangle inequality rule
        // Triangle rule: a + b > c (where c is the largest side)
        Arrays.sort(nums);
        int count = 0;  // This will store our final answer
        int n = nums.length;  // Length of the array
        
        // Start from the largest element and move backwards
        // We need at least 3 elements, so i starts from 2 (third element from end)
        for (int i = n - 1; i >= 2; i--) {
            // 'i' represents the index of the largest side (c) in current triplet
            
            int left = 0;      // Pointer to smallest element
            int right = i - 1; // Pointer to element just before the largest side
            
            // Use two pointers to find valid pairs (a, b) such that a + b > c
            while (left < right) {
                // Check if current pair (nums[left], nums[right]) can form triangle with nums[i]
                if (nums[left] + nums[right] > nums[i]) {
                    // If nums[left] + nums[right] > nums[i], then ALL elements between 
                    // left and right will also satisfy the condition (since array is sorted)
                    // So we can add (right - left) triplets to our count
                    count += right - left;
                    
                    // Move right pointer leftwards to try smaller b values
                    right--;
                } else {
                    // If sum is too small, we need larger a value
                    // Move left pointer rightwards
                    left++;
                }
            }
        }
        
        return count;
    }
}
