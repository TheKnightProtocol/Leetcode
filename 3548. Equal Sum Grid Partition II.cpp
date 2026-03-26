#include <vector>
#include <algorithm>

using namespace std;

class Solution {
    // Using static arrays to avoid repeated allocations across test cases
    int firstRow[100001], lastRow[100001];
    int firstCol[100001], lastCol[100001];

public:
    bool canPartitionGrid(vector<vector<int>>& grid) {
        int m = grid.size();
        int n = grid[0].size();
        long long totalSum = 0;
        vector<long long> rowSums(m, 0), colSums(n, 0);

        for (int i = 0; i <= 100000; ++i) {
            firstRow[i] = m; lastRow[i] = -1;
            firstCol[i] = n; lastCol[i] = -1;
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int v = grid[i][j];
                totalSum += v;
                rowSums[i] += v;
                colSums[j] += v;
                if (i < firstRow[v]) firstRow[v] = i;
                if (i > lastRow[v]) lastRow[v] = i;
                if (j < firstCol[v]) firstCol[v] = j;
                if (j > lastCol[v]) lastCol[v] = j;
            }
        }

        // Horizontal Cuts
        long long upper = 0;
        for (int i = 0; i < m - 1; ++i) {
            upper += rowSums[i];
            long long lower = totalSum - upper;
            if (upper == lower) return true;
            
            // Check Upper Section
            long long d1 = upper - lower;
            if (d1 > 0 && d1 <= 100000 && (i + 1) * (long long)n > 1) {
                if (i > 0 && n > 1) { // 2D block: does d1 exist in rows [0, i]?
                    if (firstRow[d1] <= i) return true;
                } else if (i == 0) { // Single row
                    if (grid[0][0] == d1 || grid[0][n-1] == d1) return true;
                } else if (n == 1) { // Single column
                    if (grid[0][0] == d1 || grid[i][0] == d1) return true;
                }
            }
            // Check Lower Section
            long long d2 = lower - upper;
            if (d2 > 0 && d2 <= 100000 && (m - 1 - i) * (long long)n > 1) {
                if (m - 1 - i > 1 && n > 1) { // 2D block: does d2 exist in rows [i+1, m-1]?
                    if (lastRow[d2] >= i + 1) return true;
                } else if (m - 1 - i == 1) { // Single row
                    if (grid[i+1][0] == d2 || grid[i+1][n-1] == d2) return true;
                } else if (n == 1) { // Single column
                    if (grid[i+1][0] == d2 || grid[m-1][0] == d2) return true;
                }
            }
        }

        // Vertical Cuts
        long long left = 0;
        for (int j = 0; j < n - 1; ++j) {
            left += colSums[j];
            long long right = totalSum - left;
            if (left == right) return true;

            // Check Left Section
            long long d1 = left - right;
            if (d1 > 0 && d1 <= 100000 && (long long)m * (j + 1) > 1) {
                if (m > 1 && j > 0) { // 2D block
                    if (firstCol[d1] <= j) return true;
                } else if (j == 0) { // Single column
                    if (grid[0][0] == d1 || grid[m-1][0] == d1) return true;
                } else if (m == 1) { // Single row
                    if (grid[0][0] == d1 || grid[0][j] == d1) return true;
                }
            }
            // Check Right Section
            long long d2 = right - left;
            if (d2 > 0 && d2 <= 100000 && (long long)m * (n - 1 - j) > 1) {
                if (m > 1 && n - 1 - j > 1) { // 2D block
                    if (lastCol[d2] >= j + 1) return true;
                } else if (n - 1 - j == 1) { // Single column
                    if (grid[0][j+1] == d2 || grid[m-1][j+1] == d2) return true;
                } else if (m == 1) { // Single row
                    if (grid[0][j+1] == d2 || grid[0][n-1] == d2) return true;
                }
            }
        }
        return false;
    }
};
