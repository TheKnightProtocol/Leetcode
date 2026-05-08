class Solution {
private:
    unordered_map<int, vector<int>> mp;
public:
    Solution(vector<int>& nums) {
        for (int i = 0; i < nums.size(); ++i) {
            mp[nums[i]].push_back(i);
        }
    }
    
    int pick(int target) {
        vector<int>& indices = mp[target];
        int idx = rand() % indices.size();
        return indices[idx];
    }
};
