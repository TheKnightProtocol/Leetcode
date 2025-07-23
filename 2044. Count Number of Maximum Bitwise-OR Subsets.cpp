#include <vector>
using namespace std;

class Solution {
public:
    int countMaxOrSubsets(vector<int>& nums) {
        int max_or = 0;
        for (int num : nums) {
            max_or |= num;
        }
        
        int count = 0;
        int n = nums.size();
        // Iterate all possible non-empty subsets
        for (int mask = 1; mask < (1 << n); ++mask) {
            int current_or = 0;
            for (int i = 0; i < n; ++i) {
                if (mask & (1 << i)) {
                    current_or |= nums[i];
                }
            }
            if (current_or == max_or) {
                count++;
            }
        }
        return count;
    }
};
