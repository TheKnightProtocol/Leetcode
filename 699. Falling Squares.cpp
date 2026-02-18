class Solution {
public:
    vector<int> fallingSquares(vector<vector<int>>& positions) {
        vector<int> result;
        vector<pair<int, int>> intervals;
        vector<int> heights;
        int maxHeight = 0;
        
        for (auto& pos : positions) {
            int left = pos[0];
            int side = pos[1];
            int right = left + side;
            int baseHeight = 0;
            
            for (int i = 0; i < intervals.size(); i++) {
                if (intervals[i].first < right && intervals[i].second > left) {
                    baseHeight = max(baseHeight, heights[i]);
                }
            }
            
            int currentHeight = baseHeight + side;
            
            intervals.push_back({left, right});
            heights.push_back(currentHeight);
            
            maxHeight = max(maxHeight, currentHeight);
            result.push_back(maxHeight);
        }
        
        return result;
    }
};
