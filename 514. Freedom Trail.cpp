#include <vector>
#include <string>
#include <unordered_map>
#include <algorithm>
#include <cmath>

using namespace std;

class Solution {
public:
    int findRotateSteps(string ring, string key) {
        int n = ring.size(), m = key.size();
        unordered_map<char, vector<int>> pos;
        for (int i = 0; i < n; ++i)
            pos[ring[i]].push_back(i);

        vector<vector<int>> dp(m + 1, vector<int>(n, -1));

        function<int(int, int)> dfs = [&](int idx, int rPos) -> int {
            if (idx == m) return 0;
            if (dp[idx][rPos] != -1) return dp[idx][rPos];
            int res = INT_MAX;
            for (int i : pos[key[idx]]) {
                int dist = abs(rPos - i);
                dist = min(dist, n - dist);
                res = min(res, dist + 1 + dfs(idx + 1, i));
            }
            return dp[idx][rPos] = res;
        };

        return dfs(0, 0);
    }
};
