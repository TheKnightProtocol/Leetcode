class Solution {
public:
    vector<int> arrayRankTransform(vector<int>& arr) {
        unordered_map<int, int> numToRank;
        set<int> nums(arr.begin(), arr.end());
        int rank = 1;
        for (auto num : nums) {
            numToRank[num] = rank;
            rank++;
        }
        for (int i = 0; i < arr.size(); i++) {
            arr[i] = numToRank[arr[i]];
        }
        return arr;
    }
};
