#include <string>
#include <vector>
using namespace std;

class Solution {
public:
    char processStr(string s, long long k) {
        int n = s.size();
        vector<long long> len(n + 1, 0);
        for (int i = 0; i < n; ++i) {
            len[i + 1] = len[i];
            if (s[i] >= 'a') len[i + 1]++;
            else if (s[i] == '*') len[i + 1] = max(0LL, len[i + 1] - 1);
            else if (s[i] == '#') len[i + 1] *= 2;
        }
        if (k >= len[n]) return '.';
        for (int i = n - 1; i >= 0; --i) {
            char c = s[i];
            long long prv = len[i], cur = len[i + 1];
            if (c >= 'a') {
                if (k == cur - 1) return c;
                cur = prv;
            } else if (c == '*') {
                if (prv > cur) cur = prv;
            } else if (c == '#') {
                cur /= 2;
                k %= cur;
            } else if (c == '%') {
                k = cur - 1 - k;
            }
        }
        return '.';
    }
};
