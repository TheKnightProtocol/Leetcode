class Solution {
public:
    int maxIncreaseKeepingSkyline(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<int> rowMax(n, 0);
        vector<int> colMax(n, 0);

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                rowMax[r] = max(rowMax[r], grid[r][c]);
                colMax[c] = max(colMax[c], grid[r][c]);
            }
        }

        int totalIncrease = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                totalIncrease += min(rowMax[r], colMax[c]) - grid[r][c];
            }
        }

        return totalIncrease;
    }
};
