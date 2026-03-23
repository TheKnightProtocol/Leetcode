#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

class Solution {
public:
    int maxProductPath(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        const int MOD = 1e9 + 7;
        
        vector<vector<long long>> maxProd(m, vector<long long>(n));
        vector<vector<long long>> minProd(m, vector<long long>(n));
        
        maxProd[0][0] = minProd[0][0] = grid[0][0];
        
        for (int i = 1; i < m; i++) {
            maxProd[i][0] = minProd[i][0] = maxProd[i-1][0] * grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            maxProd[0][j] = minProd[0][j] = maxProd[0][j-1] * grid[0][j];
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] >= 0) {
                    maxProd[i][j] = max(maxProd[i-1][j], maxProd[i][j-1]) * grid[i][j];
                    minProd[i][j] = min(minProd[i-1][j], minProd[i][j-1]) * grid[i][j];
                } else {
                    maxProd[i][j] = min(minProd[i-1][j], minProd[i][j-1]) * grid[i][j];
                    minProd[i][j] = max(maxProd[i-1][j], maxProd[i][j-1]) * grid[i][j];
                }
            }
        }
        
        long long result = maxProd[m-1][n-1];
        if (result < 0) return -1;
        return result % MOD;
    }
};
