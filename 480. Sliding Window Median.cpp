#include <vector>
#include <set>
using namespace std;

class Solution {
public:
    vector<double> medianSlidingWindow(vector<int>& nums, int k) {
        multiset<int> low, high;
        vector<double> result;

        auto balance = [&]() {
            while (low.size() > high.size())
                high.insert(*low.rbegin()), low.erase(prev(low.end()));
            while (high.size() > low.size() + 1)
                low.insert(*high.begin()), high.erase(high.begin());
        };

        auto insert = [&](int num) {
            if (high.empty() || num >= *high.begin()) high.insert(num);
            else low.insert(num);
            balance();
        };

        auto erase = [&](int num) {
            if (high.find(num) != high.end()) high.erase(high.find(num));
            else low.erase(low.find(num));
            balance();
        };

        for (int i = 0; i < nums.size(); ++i) {
            insert(nums[i]);
            if (i >= k - 1) {
                if (k % 2 == 0)
                    result.push_back(((double)*low.rbegin() + *high.begin()) / 2.0);
                else
                    result.push_back(*high.begin());
                erase(nums[i - k + 1]);
            }
        }
        return result;
    }
};
