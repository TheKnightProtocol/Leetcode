class Solution {
public:
    bool areSimilar(vector<vector<int>>& mat, int k) {
        int m = mat.size();
        int n = mat[0].size();
        
        for (int i = 0; i < m; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] != mat[i][(j + k) % n]) {
                        return false;
                    }
                }
            } else {
                for (int j = 0; j < n; j++) {
                    int shift = (j - (k % n) + n) % n;
                    if (mat[i][j] != mat[i][shift]) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
};
