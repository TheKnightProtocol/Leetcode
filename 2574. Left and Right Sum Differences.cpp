class Solution {
public:
    vector<int> leftRightDifference(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        int total = 0;
        for (int x : nums) total += x;
        int left = 0;
        for (int i = 0; i < n; ++i) {
            int right = total - left - nums[i];
            ans[i] = abs(left - right);
            left += nums[i];
        }
        return ans;
    }
};
