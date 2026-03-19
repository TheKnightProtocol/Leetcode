class Solution {
public:
    int numberOfSubmatrices(vector<vector<char>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        
        vector<vector<int>> prefixX(m + 1, vector<int>(n + 1, 0));
        vector<vector<int>> prefixY(m + 1, vector<int>(n + 1, 0));
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                prefixX[i + 1][j + 1] = prefixX[i][j + 1] + prefixX[i + 1][j] - prefixX[i][j] + (grid[i][j] == 'X');
                prefixY[i + 1][j + 1] = prefixY[i][j + 1] + prefixY[i + 1][j] - prefixY[i][j] + (grid[i][j] == 'Y');
            }
        }
        
        int result = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int xCount = prefixX[i + 1][j + 1];
                int yCount = prefixY[i + 1][j + 1];
                
                if (xCount == yCount && xCount > 0) {
                    result++;
                }
            }
        }
        
        return result;
    }
};
