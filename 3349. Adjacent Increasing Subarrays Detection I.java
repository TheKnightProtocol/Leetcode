import java.util.List;

class Solution {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        // Convert List to array for easier access
        int n = nums.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums.get(i);
        }
        
        // Check all possible starting positions
        for (int i = 0; i <= n - 2 * k; i++) {
            if (isStrictlyIncreasing(arr, i, i + k - 1) && 
                isStrictlyIncreasing(arr, i + k, i + 2 * k - 1)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isStrictlyIncreasing(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            if (arr[i] >= arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
