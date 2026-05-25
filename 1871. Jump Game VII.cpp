class Solution {
public:
    bool canReach(string s, int minJump, int maxJump) {
        int n = s.size();
        vector<bool> dp(n, false);
        dp[0] = true;
        vector<int> prefix(n + 1, 0);
        prefix[1] = 1;
        for (int i = 1; i < n; ++i) {
            if (s[i] == '0') {
                int left = max(0, i - maxJump);
                int right = i - minJump;
                if (right >= 0 && prefix[right + 1] - prefix[left] > 0)
                    dp[i] = true;
            }
            prefix[i + 1] = prefix[i] + (dp[i] ? 1 : 0);
        }
        return dp[n - 1];
    }
};
