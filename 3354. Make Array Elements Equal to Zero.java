class Solution {
    public int countValidSelections(int[] nums) {
        int n = nums.length;
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                // Check if starting at position i with right direction is valid
                if (isValid(nums, i, 1)) {
                    count++;
                }
                // Check if starting at position i with left direction is valid  
                if (isValid(nums, i, -1)) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private boolean isValid(int[] nums, int start, int initialDir) {
        int n = nums.length;
        int[] temp = nums.clone();
        int pos = start;
        int dir = initialDir;
        
        while (true) {
            if (pos < 0 || pos >= n) {
                // Check if all elements are zero
                for (int num : temp) {
                    if (num != 0) return false;
                }
                return true;
            }
            
            if (temp[pos] == 0) {
                pos += dir;
            } else {
                temp[pos]--;
                dir = -dir;
                pos += dir;
            }
            
            // Safety check to prevent infinite loops
            if (isAllZero(temp)) {
                return true;
            }
        }
    }
    
    private boolean isAllZero(int[] arr) {
        for (int num : arr) {
            if (num != 0) return false;
        }
        return true;
    }
}
