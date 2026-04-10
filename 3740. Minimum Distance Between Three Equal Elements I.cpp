#include <vector>
#include <unordered_map>
#include <climits>
#include <algorithm>

class Solution {
public:
    int minimumDistance(std::vector<int>& nums) {
        std::unordered_map<int, std::vector<int>> pos;
        
        for (int i = 0; i < nums.size(); ++i) {
            pos[nums[i]].push_back(i);
        }
        
        int minDist = INT_MAX;
        
        for (auto& [val, indices] : pos) {
            if (indices.size() >= 3) {
                for (int i = 0; i + 2 < indices.size(); ++i) {
                    int dist = 2 * (indices[i+2] - indices[i]);
                    if (dist < minDist) {
                        minDist = dist;
                    }
                }
            }
        }
        
        return minDist == INT_MAX ? -1 : minDist;
    }
};
