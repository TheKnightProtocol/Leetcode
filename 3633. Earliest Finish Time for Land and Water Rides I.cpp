#include <vector>
#include <algorithm>
#include <climits>
using namespace std;

class Solution {
public:
    int earliestFinishTime(vector<int>& landStartTime, vector<int>& landDuration,
                           vector<int>& waterStartTime, vector<int>& waterDuration) {
        int n = landStartTime.size();
        int m = waterStartTime.size();
        int ans = INT_MAX;

        for (int i = 0; i < n; i++) {
            int landStart = landStartTime[i];
            int landEnd = landStart + landDuration[i];

            for (int j = 0; j < m; j++) {
                int waterStart = waterStartTime[j];
                int waterEnd = waterStart + waterDuration[j];

                // land → water
                ans = min(ans, max(landEnd, waterStart) + waterDuration[j]);

                // water → land
                ans = min(ans, max(waterEnd, landStart) + landDuration[i]);
            }
        }
        return ans;
    }
};
