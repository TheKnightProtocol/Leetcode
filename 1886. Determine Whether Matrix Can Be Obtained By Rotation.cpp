class Solution {
public:
    bool findRotation(vector<vector<int>>& mat, vector<vector<int>>& target) {
        int n = mat.size();
        
        if (mat == target) {
            return true;
        }
        
        bool is90 = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[j][n - 1 - i]) {
                    is90 = false;
                    break;
                }
            }
            if (!is90) break;
        }
        if (is90) return true;
        
        bool is180 = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) {
                    is180 = false;
                    break;
                }
            }
            if (!is180) break;
        }
        if (is180) return true;
        
        bool is270 = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != target[n - 1 - j][i]) {
                    is270 = false;
                    break;
                }
            }
            if (!is270) break;
        }
        if (is270) return true;
        
        return false;
    }
};
