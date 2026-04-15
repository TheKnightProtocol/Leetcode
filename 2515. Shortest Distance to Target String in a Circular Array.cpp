#include <vector>
#include <string>
#include <algorithm>
#include <cmath>

using namespace std;

class Solution {
public:
    int closestTarget(vector<string>& words, string target, int startIndex) {
        int n = words.size();
        int minDistance = -1;

        for (int i = 0; i < n; i++) {
            if (words[i] == target) {
                int directDist = abs(i - startIndex);
                int currentDist = min(directDist, n - directDist);
                
                if (minDistance == -1 || currentDist < minDistance) {
                    minDistance = currentDist;
                }
            }
        }

        return minDistance;
    }
};
