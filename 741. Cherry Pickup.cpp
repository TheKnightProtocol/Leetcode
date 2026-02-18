class Solution {
public:
    int cherryPickup(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<vector<int>>> dp(n, vector<vector<int>>(n, vector<int>(n, INT_MIN)));
        
        return max(0, dfs(grid, dp, 0, 0, 0, n));
    }
    
private:
    int dfs(vector<vector<int>>& grid, vector<vector<vector<int>>>& dp, 
            int r1, int c1, int r2, int n) {
        int c2 = r1 + c1 - r2;
        
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n || 
            grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return INT_MIN;
        }
        
        if (dp[r1][c1][r2] != INT_MIN) {
            return dp[r1][c1][r2];
        }
        
        if (r1 == n-1 && c1 == n-1) {
            return grid[r1][c1];
        }
        
        int cherries = grid[r1][c1];
        if (r1 != r2 || c1 != c2) {
            cherries += grid[r2][c2];
        }
        
        int next = max({
            dfs(grid, dp, r1+1, c1, r2+1, n),
            dfs(grid, dp, r1+1, c1, r2, n),
            dfs(grid, dp, r1, c1+1, r2+1, n),
            dfs(grid, dp, r1, c1+1, r2, n)
        });
        
        cherries += next;
        dp[r1][c1][r2] = cherries;
        return cherries;
    }
};
