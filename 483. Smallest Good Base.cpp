#include <cmath>
#include <string>
using namespace std;

class Solution {
public:
    string smallestGoodBase(string n) {
        unsigned long long num = stoull(n);

        for (int m = log2(num); m >= 1; --m) {
            unsigned long long k = pow(num, 1.0 / m);
            if (k <= 1) continue;

            unsigned long long sum = 1, cur = 1;
            for (int i = 1; i <= m; ++i) {
                cur *= k;
                sum += cur;
            }

            if (sum == num)
                return to_string(k);
        }
        return to_string(num - 1);
    }
};
