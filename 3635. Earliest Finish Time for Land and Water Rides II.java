#include <vector>
#include <algorithm>
#include <climits>
using namespace std;

class Solution {
public:
    int earliestFinishTime(vector<int>& ls, vector<int>& ld, vector<int>& ws, vector<int>& wd) {
        int n = ls.size(), m = ws.size(), ans = INT_MAX;
        vector<pair<int,int>> L(n), W(m);
        for (int i = 0; i < n; i++) L[i] = {ls[i], ld[i]};
        for (int i = 0; i < m; i++) W[i] = {ws[i], wd[i]};
        sort(L.begin(), L.end()); sort(W.begin(), W.end());

        vector<int> Ls(n), Ld(n), Le(n), Ws(m), Wd(m), We(m);
        for (int i = 0; i < n; i++) Ls[i]=L[i].first, Ld[i]=L[i].second, Le[i]=L[i].first+L[i].second;
        for (int i = 0; i < m; i++) Ws[i]=W[i].first, Wd[i]=W[i].second, We[i]=W[i].first+W[i].second;

        vector<int> prefLd(n), suffLe(n), prefWd(m), suffWe(m);
        prefLd[0] = Ld[0]; suffLe[n-1] = Le[n-1];
        for (int i = 1; i < n; i++) prefLd[i] = min(prefLd[i-1], Ld[i]);
        for (int i = n-2; i >= 0; i--) suffLe[i] = min(suffLe[i+1], Le[i]);
        prefWd[0] = Wd[0]; suffWe[m-1] = We[m-1];
        for (int i = 1; i < m; i++) prefWd[i] = min(prefWd[i-1], Wd[i]);
        for (int i = m-2; i >= 0; i--) suffWe[i] = min(suffWe[i+1], We[i]);

        auto solve = [&](int e, vector<int>& starts, vector<int>& suffEnd, vector<int>& prefDur, int lim) {
            int idx = lower_bound(starts.begin(), starts.end(), e) - starts.begin();
            if (idx < lim) ans = min(ans, suffEnd[idx]);
            if (idx > 0) ans = min(ans, e + prefDur[idx-1]);
        };

        for (int e : Le) solve(e, Ws, suffWe, prefWd, m);
        for (int e : We) solve(e, Ls, suffLe, prefLd, n);
        return ans;
    }
};
