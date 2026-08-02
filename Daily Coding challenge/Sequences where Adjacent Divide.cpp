class Solution {
	public:
	int count(int n, int m) {
		// code here
		vector<int> dp(m + 1, 1);
		while (--n) {
			vector<int> next(m + 1, 0);
			for (int j = 1; j <= m ; ++j)
				for (int k = 1; k <= m; ++k)
					if (j % k == 0 || k % j == 0)
						next[j] += dp[k];
			dp = next;
		}
		return accumulate(dp.begin() + 1, dp.end(), 0);
	}
};
