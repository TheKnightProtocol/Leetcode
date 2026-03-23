class Solution { 
public:
    vector<vector<int>> minAbsDiff(vector<vector<int>>& grid, int k) {
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<int>> result(m - k + 1, vector<int>(n - k + 1, 0));
        
        for (int i = 0; i <= m - k; i++) {
            for (int j = 0; j <= n - k; j++) {
                set<int> values;
                for (int x = i; x < i + k; x++) {
                    for (int y = j; y < j + k; y++) {
                        values.insert(grid[x][y]);
                    }
                }
                
                if (values.size() == 1) {
                    result[i][j] = 0;
                    continue;
                }
                
                int min_diff = INT_MAX;
                auto prev = values.begin();
                auto curr = next(prev);
                
                while (curr != values.end()) {
                    min_diff = min(min_diff, *curr - *prev);
                    prev = curr;
                    curr++;
                }
                
                result[i][j] = min_diff;
            }
        }
        
        return result;
    }
};
