class Solution {
public:
    int countSubmatrices(vector<vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<int>> prefix(m, vector<int>(n));
        int ans = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    prefix[i][j] = grid[i][j];
                } else if (i == 0) {
                    prefix[i][j] = prefix[i][j-1] + grid[i][j];
                } else if (j == 0) {
                    prefix[i][j] = prefix[i-1][j] + grid[i][j];
                } else {
                    prefix[i][j] = prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1] + grid[i][j];
                }
                
                if (prefix[i][j] <= k) {
                    ans++;
                }
            }
        }
        
        return ans;
    }
};
