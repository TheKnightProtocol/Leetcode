#include <vector>
#include <numeric>
#include <cmath>
using namespace std;

class Solution {
public:
    int findTargetSumWays(vector<int>& nums, int target) {
        int total = accumulate(nums.begin(), nums.end(), 0);
        if (abs(target) > total || (total + target) % 2 != 0) return 0;
        int subsetSum = (total + target) / 2;
        vector<int> dp(subsetSum + 1, 0);
        dp[0] = 1;
        for (int num : nums) {
            for (int s = subsetSum; s >= num; --s) {
                dp[s] += dp[s - num];
            }
        }
        return dp[subsetSum];
    }
};
