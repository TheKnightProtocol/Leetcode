#include <vector>
#include <algorithm>
#include <numeric>

class Solution {
public:
    int minimumBoxes(std::vector<int>& apple, std::vector<int>& capacity) {
        int totalApples = std::accumulate(apple.begin(), apple.end(), 0);
        std::sort(capacity.rbegin(), capacity.rend());
        
        int usedBoxes = 0;
        int currentCapacity = 0;
        
        for (int cap : capacity) {
            currentCapacity += cap;
            usedBoxes++;
            if (currentCapacity >= totalApples) {
                break;
            }
        }
        
        return usedBoxes;
    }
};
