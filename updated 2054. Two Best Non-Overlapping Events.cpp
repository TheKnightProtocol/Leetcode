#include <vector>
#include <algorithm>
#include <map>
using namespace std;

class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        sort(events.begin(), events.end());
        int n = events.size();
        vector<int> suffixMax(n);
        suffixMax[n - 1] = events[n - 1][2];
        for (int i = n - 2; i >= 0; --i) {
            suffixMax[i] = max(events[i][2], suffixMax[i + 1]);
        }
        
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int l = i + 1, r = n - 1;
            int nextIdx = -1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (events[mid][0] > events[i][1]) {
                    nextIdx = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            int cur = events[i][2];
            if (nextIdx != -1) {
                cur += suffixMax[nextIdx];
            }
            ans = max(ans, cur);
        }
        return ans;
    }
};
