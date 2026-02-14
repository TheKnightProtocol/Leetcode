class Solution {
public:
    double champagneTower(int poured, int query_row, int query_glass) {
        vector<double> dp(101, 0);
        dp[0] = poured;
        
        for(int row = 0; row < query_row; row++) {
            vector<double> next(101, 0);
            for(int col = 0; col <= row; col++) {
                if(dp[col] > 1) {
                    double excess = (dp[col] - 1) / 2.0;
                    next[col] += excess;
                    next[col + 1] += excess;
                }
            }
            dp = next;
        }
        
        return min(1.0, dp[query_glass]);
    }
};
