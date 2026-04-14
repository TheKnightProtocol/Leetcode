#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

class Solution {
public:
    long long minimumTotalDistance(vector<int>& robot, vector<vector<int>>& factory) {
        sort(robot.begin(), robot.end());
        sort(factory.begin(), factory.end());

        vector<int> f_positions;
        for (const auto& f : factory) {
            for (int i = 0; i < f[1]; ++i) {
                f_positions.push_back(f[0]);
            }
        }

        int n = robot.size();
        int m = f_positions.size();

        vector<vector<long long>> dp(n + 1, vector<long long>(m + 1, 1e15));

        for (int j = 0; j <= m; ++j) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                dp[i][j] = dp[i][j - 1];
                long long dist = abs(robot[i - 1] - f_positions[j - 1]);
                if (dp[i - 1][j - 1] != 1e15) {
                    dp[i][j] = min(dp[i][j], dp[i - 1][j - 1] + dist);
                }
            }
        }

        return dp[n][m];
    }
};
