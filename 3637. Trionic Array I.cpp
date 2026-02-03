class Solution {
public:
    bool isTrionic(vector<int>& nums) {
        int n = nums.size();
        
        if (n < 3) return false;
        
        vector<bool> left_inc(n, false);
        vector<bool> right_inc(n, false);
        
        left_inc[0] = true;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1] && left_inc[i - 1]) {
                left_inc[i] = true;
            }
        }
        
        right_inc[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1] && right_inc[i + 1]) {
                right_inc[i] = true;
            }
        }
        
        for (int p = 1; p < n - 2; p++) {
            for (int q = p + 1; q < n - 1; q++) {
                
                if (!left_inc[p]) continue;
                if (!right_inc[q]) continue;
                
                bool valid = true;
                for (int i = p; i < q; i++) {
                    if (nums[i] <= nums[i + 1]) {
                        valid = false;
                        break;
                    }
                }
                
                if (valid) return true;
            }
        }
        
        return false;
    }
};
