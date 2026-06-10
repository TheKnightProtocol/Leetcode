#include <vector>
#include <deque>
#include <stack>
#include <algorithm>
using namespace std;
using ll = long long;

class Solution {
public:
    ll maxTotalValue(vector<int>& nums, int k) {
        int n = nums.size();
        ll total_sub = (ll)n * (n + 1) / 2;
        if (k > total_sub) return -1;

        vector<int> leftGr(n, -1), rightGr(n, n), leftSm(n, -1), rightSm(n, n);
        stack<int> st;
        for (int i = 0; i < n; ++i) {
            while (!st.empty() && nums[st.top()] <= nums[i]) st.pop();
            leftGr[i] = st.empty() ? -1 : st.top();
            st.push(i);
        }
        while (!st.empty()) st.pop();
        for (int i = n - 1; i >= 0; --i) {
            while (!st.empty() && nums[st.top()] < nums[i]) st.pop();
            rightGr[i] = st.empty() ? n : st.top();
            st.push(i);
        }
        while (!st.empty()) st.pop();
        for (int i = 0; i < n; ++i) {
            while (!st.empty() && nums[st.top()] >= nums[i]) st.pop();
            leftSm[i] = st.empty() ? -1 : st.top();
            st.push(i);
        }
        while (!st.empty()) st.pop();
        for (int i = n - 1; i >= 0; --i) {
            while (!st.empty() && nums[st.top()] > nums[i]) st.pop();
            rightSm[i] = st.empty() ? n : st.top();
            st.push(i);
        }

        ll totalMaxSum = 0, totalMinSum = 0;
        for (int i = 0; i < n; ++i) {
            totalMaxSum += (ll)nums[i] * (i - leftGr[i]) * (rightGr[i] - i);
            totalMinSum += (ll)nums[i] * (i - leftSm[i]) * (rightSm[i] - i);
        }
        ll totalSum = totalMaxSum - totalMinSum;

        int maxVal = *max_element(nums.begin(), nums.end());
        int minVal = *min_element(nums.begin(), nums.end());
        int hi = maxVal - minVal, lo = 0;

        auto lessThan = [&](int V) -> pair<ll, ll> {
            if (V <= 0) return {0, 0};
            struct Seg { ll val, cnt; };
            deque<Seg> maxq, minq;
            ll sum_max = 0, sum_min = 0;
            ll cnt = 0, sum = 0;
            int L = 0;
            for (int R = 0; R < n; ++R) {
                ll add_cnt = 1;
                while (!maxq.empty() && maxq.back().val <= nums[R]) {
                    add_cnt += maxq.back().cnt;
                    sum_max -= maxq.back().val * maxq.back().cnt;
                    maxq.pop_back();
                }
                maxq.push_back({nums[R], add_cnt});
                sum_max += nums[R] * add_cnt;

                add_cnt = 1;
                while (!minq.empty() && minq.back().val >= nums[R]) {
                    add_cnt += minq.back().cnt;
                    sum_min -= minq.back().val * minq.back().cnt;
                    minq.pop_back();
                }
                minq.push_back({nums[R], add_cnt});
                sum_min += nums[R] * add_cnt;

                while (L <= R && maxq.front().val - minq.front().val >= V) {
                    ll maxv = maxq.front().val;
                    if (--maxq.front().cnt == 0) maxq.pop_front();
                    sum_max -= maxv;

                    ll minv = minq.front().val;
                    if (--minq.front().cnt == 0) minq.pop_front();
                    sum_min -= minv;

                    ++L;
                }
                ll valid = R - L + 1;
                cnt += valid;
                sum += sum_max - sum_min;
            }
            return {cnt, sum};
        };

        ll V = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            auto [less_cnt, _] = lessThan(mid);
            ll ge_cnt = total_sub - less_cnt;
            if (ge_cnt >= k) {
                V = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        auto [less_cnt, less_sum] = lessThan(V + 1);
        ll ge_cnt = total_sub - less_cnt;
        ll ge_sum = totalSum - less_sum;
        return ge_sum + (k - ge_cnt) * V;
    }
};
