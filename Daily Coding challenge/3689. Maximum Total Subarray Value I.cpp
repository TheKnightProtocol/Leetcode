#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    long long maxTotalValue(vector<int>& nums, int k) {
        auto [mn, mx] = minmax_element(nums.begin(), nums.end());
        return (long long) k * (*mx - *mn);
    }
};
