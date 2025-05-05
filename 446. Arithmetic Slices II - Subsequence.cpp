class Solution {
public:
    int numberOfArithmeticSlices(vector<int>& nums) {
        int n = nums.size();
        vector<unordered_map<long long, int>> dp(n);
        int result = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long long diff = (long long)nums[i] - nums[j];
                int count_at_j = dp[j].count(diff) ? dp[j][diff] : 0;
                dp[i][diff] += count_at_j + 1;
                result += count_at_j;  // Only subsequences of length ≥ 3
            }
        }

        return result;
    }
};
