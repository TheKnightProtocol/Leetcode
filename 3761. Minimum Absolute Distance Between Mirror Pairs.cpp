class Solution {
    int rev(int x) {
        int r = 0;
        while (x) {
            r = r * 10 + x % 10;
            x /= 10;
        }
        return r;
    }
public:
    int minMirrorPairDistance(vector<int>& nums) {
        unordered_map<int, vector<int>> pos;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            pos[nums[i]].push_back(i);
        }
        int ans = INT_MAX;
        for (auto& p : pos) {
            int val = p.first;
            vector<int>& idxs = p.second;
            int r = rev(val);
            auto it = pos.find(r);
            if (it == pos.end()) continue;
            vector<int>& other = it->second;
            for (int i : idxs) {
                auto jt = lower_bound(other.begin(), other.end(), i + 1);
                if (jt != other.end()) {
                    ans = min(ans, *jt - i);
                }
            }
        }
        return ans == INT_MAX ? -1 : ans;
    }
};
