class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int left = 1, right = n / 2;
        int answer = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (isPossible(nums, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
    
    private boolean isPossible(List<Integer> nums, int k) {
        int n = nums.size();
        int count = 0;
        
        for (int i = 0; i <= n - 2 * k; i++) {
            // Check first subarray [i, i+k-1]
            boolean firstValid = true;
            for (int j = i + 1; j < i + k; j++) {
                if (nums.get(j) <= nums.get(j - 1)) {
                    firstValid = false;
                    break;
                }
            }
            
            // Check second subarray [i+k, i+2k-1]
            boolean secondValid = true;
            for (int j = i + k + 1; j < i + 2 * k; j++) {
                if (nums.get(j) <= nums.get(j - 1)) {
                    secondValid = false;
                    break;
                }
            }
            
            if (firstValid && secondValid) {
                return true;
            }
        }
        
        return false;
    }
}
