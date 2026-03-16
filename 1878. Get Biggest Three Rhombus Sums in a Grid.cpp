class Solution {
public:
    vector<int> getBiggestThree(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        set<int> sums;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sums.insert(grid[i][j]);
                
                int maxK = min({i, m - 1 - i, j, n - 1 - j});
                
                for (int k = 1; k <= maxK; k++) {
                    int sum = 0;
                    
                    for (int step = 0; step <= k; step++) {
                        sum += grid[i - k + step][j - step];
                    }
                    
                    for (int step = 1; step <= k; step++) {
                        sum += grid[i + step][j - k + step];
                    }
                    
                    for (int step = 1; step <= k; step++) {
                        sum += grid[i + k - step][j + step];
                    }
                    
                    for (int step = 1; step < k; step++) {
                        sum += grid[i - step][j + k - step];
                    }
                    
                    sums.insert(sum);
                }
            }
        }
        
        vector<int> result(sums.rbegin(), sums.rend());
        if (result.size() > 3) {
            result.resize(3);
        }
        
        return result;
    }
};
