#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maxWalls(vector<int>& robots, vector<int>& distance, vector<int>& walls) {
        int n = robots.size();
        vector<pair<long long, long long>> r(n);
        for (int i = 0; i < n; ++i) {
            r[i] = {(long long)robots[i], (long long)distance[i]};
        }
        sort(r.begin(), r.end());

        vector<long long> w;
        w.reserve(walls.size());
        for (int wall : walls) w.push_back((long long)wall);
        sort(w.begin(), w.end());

        auto count = [&](long long left, long long right) {
            if (left > right) return 0;
            return (int)(upper_bound(w.begin(), w.end(), right) - lower_bound(w.begin(), w.end(), left));
        };

        vector<vector<int>> dp(n + 1, vector<int>(2, 0));

        for (int i = 0; i < n; ++i) {
            long long pos = r[i].first;
            long long dist = r[i].second;

            long long leftLimit = (i > 0) ? r[i-1].first + 1 : -2e9;
            long long startL = max(leftLimit, pos - dist);
            
            dp[i+1][0] = max(dp[i+1][0], dp[i][0] + count(startL, pos));
            
            if (i > 0) {
                long long prevRightReach = min(pos - 1, r[i-1].first + r[i-1].second);
                long long nonOverlappingStartL = max(prevRightReach + 1, startL);
                dp[i+1][0] = max(dp[i+1][0], dp[i][1] + count(nonOverlappingStartL, pos));
            }

            long long rightLimit = (i < n - 1) ? r[i+1].first - 1 : 2e9;
            long long endR = min(rightLimit, pos + dist);
            
            int wallsRight = count(pos, endR);
            dp[i+1][1] = max(dp[i+1][1], dp[i][0] + wallsRight);
            if (i > 0) {
                dp[i+1][1] = max(dp[i+1][1], dp[i][1] + count(pos + 1, endR));
            }

            dp[i+1][0] = max({dp[i+1][0], dp[i][0], dp[i][1]});
        }

        return max(dp[n][0], dp[n][1]);
    }
};
