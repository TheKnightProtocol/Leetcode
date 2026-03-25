class Solution {
public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        long long totalSum = 0;

        vector<long long> rowSums(m, 0);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rowSums[i] += grid[i][j];
            }
            totalSum += rowSums[i];
        }

        if (totalSum % 2 != 0) return false;
        long long target = totalSum / 2;

        long long currentTopSum = 0;
        for (int i = 0; i < m - 1; ++i) {
            currentTopSum += rowSums[i];
            if (currentTopSum == target) return true;
        }

        long long currentLeftSum = 0;
        for (int j = 0; j < n - 1; ++j) {
            for (int i = 0; i < m; ++i) {
                currentLeftSum += grid[i][j];
            }
            if (currentLeftSum == target) return true;
        }

        return false;
    }
};
