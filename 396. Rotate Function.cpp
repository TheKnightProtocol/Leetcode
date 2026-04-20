class Solution {
public:
    int maxRotateFunction(vector<int>& nums) {
        long long n = nums.size();
        long long sum = 0;
        long long F = 0;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            F += (long long)i * nums[i];
        }
        long long maxF = F;
        for (int k = 1; k < n; ++k) {
            F = F + sum - n * nums[n - k];
            if (F > maxF) maxF = F;
        }
        return (int)maxF;
    }
};
