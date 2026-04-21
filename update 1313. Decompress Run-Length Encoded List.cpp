class Solution {
public:
    vector<int> decompressRLElist(vector<int>& nums) {
        vector<int> result;
        for (int i = 0; i < nums.size(); i += 2) {
            int freq = nums[i];
            int val = nums[i + 1];
            result.insert(result.end(), freq, val);
        }
        return result;
    }
};
