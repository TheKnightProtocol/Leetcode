class Solution {
private:
    const int MOD = 1337;

    int powmod(int base, int exp) {
        int res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp & 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return res;
    }

public:
    int superPow(int a, vector<int>& b) {
        int ans = 1;
        for (int d : b) {
            ans = powmod(ans, 10) * powmod(a, d) % MOD;
        }
        return ans;
    }
};
