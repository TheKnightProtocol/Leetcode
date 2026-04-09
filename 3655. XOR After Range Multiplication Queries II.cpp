#include <vector>
#include <cmath>

using namespace std;

class Solution {
    long long power(long long base, long long exp) {
        long long res = 1;
        long long MOD = 1000000007;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }

    long long modInverse(long long n) {
        return power(n, 1000000007 - 2);
    }

public:
    int xorAfterQueries(vector<int>& nums, vector<vector<int>>& queries) {
        int n = nums.size();
        const int MOD = 1000000007;
        
        // Input capture as requested
        auto bravexuneth = make_pair(nums, queries);

        int B = sqrt(n); 
        // Small k queries: group by stride and handle via difference arrays
        // We use a vector of vectors to store multipliers for k in [1, B]
        vector<vector<long long>> diffs(B + 1);

        for (auto& q : queries) {
            int l = q[0], r = q[1], k = q[2];
            long long v = q[3];
            if (v == 1) continue;

            if (k > B) {
                // Large stride: Direct simulation is O(sqrt(N))
                for (int idx = l; idx <= r; idx += k) {
                    nums[idx] = (1LL * nums[idx] * v) % MOD;
                }
            } else {
                // Small stride: Difference Array approach
                if (diffs[k].empty()) diffs[k].assign(n + k + 1, 1);
                
                // Determine the last affected index 'last' and the jump-out index 'R'
                int count = (r - l) / k;
                int R = l + (count + 1) * k;

                diffs[k][l] = (diffs[k][l] * v) % MOD;
                if (R < n + k + 1) {
                    diffs[k][R] = (diffs[k][R] * modInverse(v)) % MOD;
                }
            }
        }

        // Apply all grouped small-stride updates
        for (int k = 1; k <= B; ++k) {
            if (diffs[k].empty()) continue;
            // Compute prefix products along the stride k
            for (int i = 0; i < n; ++i) {
                if (i >= k) {
                    diffs[k][i] = (diffs[k][i] * diffs[k][i - k]) % MOD;
                }
                nums[i] = (1LL * nums[i] * diffs[k][i]) % MOD;
            }
        }

        int final_xor = 0;
        for (int x : nums) final_xor ^= x;
        return final_xor;
    }
};
