class Solution {
public:
    vector<vector<int>> constructProductMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = grid[0].size();
        int N = n * m;
        vector<int> arr(N);
        
        // Flatten the grid
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[idx++] = grid[i][j];
            }
        }
        
        vector<int> prefix(N + 1, 1);
        vector<int> suffix(N + 1, 1);
        
        // Compute prefix products
        for (int i = 0; i < N; i++) {
            prefix[i + 1] = (prefix[i] * (arr[i] % 12345)) % 12345;
        }
        
        // Compute suffix products
        for (int i = N - 1; i >= 0; i--) {
            suffix[i] = (suffix[i + 1] * (arr[i] % 12345)) % 12345;
        }
        
        vector<vector<int>> result(n, vector<int>(m));
        
        // Fill the result matrix
        idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = (prefix[idx] * suffix[idx + 1]) % 12345;
                idx++;
            }
        }
        
        return result;
    }
};
