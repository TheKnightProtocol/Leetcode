#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

class Solution {
public:
    int nthSuperUglyNumber(int n, vector<int>& primes) {
        vector<long long> ugly(n);
        ugly[0] = 1;
        
        int k = primes.size();
        vector<int> idx(k, 0);
        vector<long long> next_vals(k);
        
        for (int i = 0; i < k; ++i) {
            next_vals[i] = primes[i];
        }
        
        for (int i = 1; i < n; ++i) {
            long long next_ugly = *min_element(next_vals.begin(), next_vals.end());
            ugly[i] = next_ugly;
            
            for (int j = 0; j < k; ++j) {
                if (next_vals[j] == next_ugly) {
                    idx[j]++;
                    next_vals[j] = (long long)primes[j] * ugly[idx[j]];
                }
            }
        }
        
        return (int)ugly[n - 1];
    }
};
