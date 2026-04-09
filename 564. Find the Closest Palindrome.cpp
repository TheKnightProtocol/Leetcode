#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

class Solution {
public:
    string nearestPalindromic(string n) {
        int len = n.length();
        if (len == 1) return to_string(stol(n) - 1);

        vector<long> candidates;
        candidates.push_back(pow(10, len - 1) - 1);
        candidates.push_back(pow(10, len) + 1);

        long prefix = stol(n.substr(0, (len + 1) / 2));

        for (long i : {prefix - 1, prefix, prefix + 1}) {
            string p = to_string(i);
            string res = p;
            string suffix = p.substr(0, len / 2);
            reverse(suffix.begin(), suffix.end());
            res += suffix;
            candidates.push_back(stol(res));
        }

        long num = stol(n);
        long closest = -1;

        for (long cand : candidates) {
            if (cand == num) continue;
            if (closest == -1) {
                closest = cand;
            } else {
                long diff1 = abs(cand - num);
                long diff2 = abs(closest - num);
                if (diff1 < diff2) {
                    closest = cand;
                } else if (diff1 == diff2) {
                    closest = min(closest, cand);
                }
            }
        }

        return to_string(closest);
    }
};
