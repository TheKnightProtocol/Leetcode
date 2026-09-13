class Solution {
public:
    int largestOverlap(vector<vector<int>>& a, vector<vector<int>>& b) {
        map<pair<int, int>, int> m;
        int ans = 0, n = a.size();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (a[i][j])
                    for (int x = 0; x < n; x++)
                        for (int y = 0; y < n; y++)
                            if (b[x][y])
                                ans = max(ans, ++m[{x - i, y - j}]);
        return ans;
    }
};
