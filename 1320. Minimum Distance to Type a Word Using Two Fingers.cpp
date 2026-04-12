#include <vector>
#include <string>
#include <algorithm>
#include <climits>

class Solution {
public:
    int minimumDistance(std::string word) {
        int n = word.length();
        auto coord = [](char c) -> std::pair<int, int> {
            int idx = c - 'A';
            return {idx / 6, idx % 6};
        };
        
        const int INF = 1e9;
        std::vector<std::vector<int>> dp(n + 1, std::vector<int>(27, INF));
        dp[1][26] = 0;
        
        for (int i = 1; i < n; ++i) {
            char prev = word[i - 1];
            char curr = word[i];
            auto [pr, pc] = coord(prev);
            auto [cr, cc] = coord(curr);
            int moveCost = std::abs(pr - cr) + std::abs(pc - cc);
            
            for (int j = 0; j <= 26; ++j) {
                if (dp[i][j] == INF) continue;
                
                dp[i + 1][j] = std::min(dp[i + 1][j], dp[i][j] + moveCost);
                
                int idleCost = 0;
                if (j != 26) {
                    auto [jr, jc] = coord('A' + j);
                    idleCost = std::abs(jr - cr) + std::abs(jc - cc);
                }
                dp[i + 1][prev - 'A'] = std::min(dp[i + 1][prev - 'A'], dp[i][j] + idleCost);
            }
        }
        
        int ans = INF;
        for (int j = 0; j <= 26; ++j) {
            ans = std::min(ans, dp[n][j]);
        }
        return ans;
    }
};
