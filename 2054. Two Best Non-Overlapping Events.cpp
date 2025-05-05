#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        sort(events.begin(), events.end());
        int n = events.size();
        vector<pair<int, int>> best;
        int res = 0, maxVal = 0;
        for (int i = n - 1; i >= 0; --i) {
            auto it = lower_bound(best.begin(), best.end(), make_pair(events[i][1] + 1, 0));
            if (it != best.end()) res = max(res, events[i][2] + it->second);
            res = max(res, events[i][2]);
            maxVal = max(maxVal, events[i][2]);
            best.insert(best.begin(), {events[i][0], maxVal});
        }
        return res;
    }
};
