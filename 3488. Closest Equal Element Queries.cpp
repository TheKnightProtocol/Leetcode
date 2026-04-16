#include <vector>
#include <unordered_map>
#include <algorithm>

class Solution {
public:
    std::vector<int> solveQueries(std::vector<int>& nums, std::vector<int>& queries) {
        int n = nums.size();
        std::unordered_map<int, std::vector<int>> mp;
        for (int i = 0; i < n; ++i) {
            mp[nums[i]].push_back(i);
        }
        std::vector<int> ans;
        ans.reserve(queries.size());
        for (int q : queries) {
            int val = nums[q];
            auto& vec = mp[val];
            if (vec.size() == 1) {
                ans.push_back(-1);
                continue;
            }
            auto it = std::lower_bound(vec.begin(), vec.end(), q);
            int pos = it - vec.begin();
            int left = (pos - 1 + vec.size()) % vec.size();
            int right = (pos + 1) % vec.size();
            int d1 = std::min(std::abs(q - vec[left]), n - std::abs(q - vec[left]));
            int d2 = std::min(std::abs(q - vec[right]), n - std::abs(q - vec[right]));
            ans.push_back(std::min(d1, d2));
        }
        return ans;
    }
};
