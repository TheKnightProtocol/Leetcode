class Solution {
public:
    vector<vector<int>> findMatrix(vector<int>& nums) {
        vector<int> freq(nums.size() + 1, 0);
        vector<vector<int>> result;

        for (int x : nums) {
            if (freq[x] >= result.size()) {
                result.push_back({});
            }
            result[freq[x]].push_back(x);
            freq[x]++;
        }

        return result;
    }
};
