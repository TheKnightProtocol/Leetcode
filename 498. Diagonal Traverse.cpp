class Solution {
public:
    vector<int> findDiagonalOrder(vector<vector<int>>& mat) {
        int m = mat.size(), n = mat[0].size();
        vector<int> ans(m * n);
        int idx = 0;
        for (int s = 0; s < m + n - 1; ++s) {
            int iMin = max(0, s - n + 1);
            int iMax = min(s, m - 1);
            if (s % 2 == 0) {
                for (int i = iMax; i >= iMin; --i) {
                    ans[idx++] = mat[i][s - i];
                }
            } else {
                for (int i = iMin; i <= iMax; ++i) {
                    ans[idx++] = mat[i][s - i];
                }
            }
        }
        return ans;
    }
};
