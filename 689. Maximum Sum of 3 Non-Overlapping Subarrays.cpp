class Solution {
public:
    vector<int> maxSumOfThreeSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> sum(n - k + 1, 0);
        
        // Calculate sum of each subarray of length k
        for (int i = 0; i < k; i++) {
            sum[0] += nums[i];
        }
        for (int i = 1; i < sum.size(); i++) {
            sum[i] = sum[i - 1] - nums[i - 1] + nums[i + k - 1];
        }
        
        // left[i] stores the starting index of the max sum subarray of length k from left up to position i
        vector<int> left(sum.size(), 0);
        int best = 0;
        for (int i = 0; i < sum.size(); i++) {
            if (sum[i] > sum[best]) {
                best = i;
            }
            left[i] = best;
        }
        
        // right[i] stores the starting index of the max sum subarray of length k from right starting at position i
        vector<int> right(sum.size(), 0);
        best = sum.size() - 1;
        for (int i = sum.size() - 1; i >= 0; i--) {
            if (sum[i] >= sum[best]) {
                best = i;
            }
            right[i] = best;
        }
        
        // Try all possible middle subarray positions
        vector<int> result(3, 0);
        int maxTotal = 0;
        for (int i = k; i < sum.size() - k; i++) {  // Changed from <= to <
            int l = left[i - k];
            int r = right[i + k];
            int total = sum[l] + sum[i] + sum[r];
            if (total > maxTotal) {
                maxTotal = total;
                result = {l, i, r};
            }
        }
        
        return result;
    }
};
