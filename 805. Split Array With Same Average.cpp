class Solution {
public:
    bool splitArraySameAverage(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return false;
        
        int sum = accumulate(nums.begin(), nums.end(), 0);
        
        sort(nums.begin(), nums.end());
        
        for (int len = 1; len <= n/2; len++) {
            if ((sum * len) % n == 0) {
                if (canForm(nums, (sum * len) / n, len, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
private:
    bool canForm(vector<int>& nums, int target, int len, int start) {
        if (len == 0) return target == 0;
        if (start >= nums.size()) return false;
        
        if (nums[start] <= target) {
            if (canForm(nums, target - nums[start], len - 1, start + 1)) {
                return true;
            }
        }
        
        int next = start + 1;
        while (next < nums.size() && nums[next] == nums[start]) {
            next++;
        }
        
        return canForm(nums, target, len, next);
    }
};
