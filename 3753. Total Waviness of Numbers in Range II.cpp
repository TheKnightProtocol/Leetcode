#include <string>
#include <cstring>
#include <algorithm>
using namespace std;
using ll = long long;

class Solution {
public:
    ll totalWaviness(ll num1, ll num2) {
        auto f = [&](ll x) -> ll {
            if (x <= 0) return 0;
            string s = to_string(x);
            int n = s.size();
            // dp[pos][tight][started][prev1][prev2]
            ll cnt[17][2][2][11][11], sum[17][2][2][11][11];
            memset(cnt, -1, sizeof(cnt));
            
            function<ll(int,int,int,int,int)> dfsCnt = [&](int pos, int tight, int started, int prev1, int prev2) -> ll {
                if (pos == n) return started ? 1 : 0;
                if (cnt[pos][tight][started][prev1][prev2] != -1)
                    return cnt[pos][tight][started][prev1][prev2];
                int limit = tight ? s[pos] - '0' : 9;
                ll res = 0;
                for (int d = 0; d <= limit; ++d) {
                    int ntight = tight && (d == limit);
                    if (!started && d == 0) {
                        res += dfsCnt(pos+1, ntight, 0, 10, 10);
                    } else {
                        res += dfsCnt(pos+1, ntight, 1, d, prev1);
                    }
                }
                return cnt[pos][tight][started][prev1][prev2] = res;
            };
            
            function<ll(int,int,int,int,int)> dfsSum = [&](int pos, int tight, int started, int prev1, int prev2) -> ll {
                if (pos == n) return 0;
                if (sum[pos][tight][started][prev1][prev2] != -1)
                    return sum[pos][tight][started][prev1][prev2];
                int limit = tight ? s[pos] - '0' : 9;
                ll res = 0;
                for (int d = 0; d <= limit; ++d) {
                    int ntight = tight && (d == limit);
                    if (!started && d == 0) {
                        res += dfsSum(pos+1, ntight, 0, 10, 10);
                    } else {
                        int nstarted = 1;
                        int np1 = d, np2 = prev1;
                        ll add = 0;
                        if (started && prev2 != 10) {
                            if ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d))
                                add = 1;
                        }
                        ll ways = dfsCnt(pos+1, ntight, nstarted, np1, np2);
                        res += dfsSum(pos+1, ntight, nstarted, np1, np2) + add * ways;
                    }
                }
                return sum[pos][tight][started][prev1][prev2] = res;
            };
            
            // Initialise count memo first (we need it for sum)
            memset(cnt, -1, sizeof(cnt));
            dfsCnt(0, 1, 0, 10, 10); // fills count memo
            memset(sum, -1, sizeof(sum));
            return dfsSum(0, 1, 0, 10, 10);
        };
        return f(num2) - f(num1 - 1);
    }
};
