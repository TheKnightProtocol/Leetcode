class Fancy {
private:
    vector<long long> sequence;
    long long addOffset;
    long long multFactor;
    const long long MOD = 1000000007;
    
public:
    Fancy() {
        sequence.clear();
        addOffset = 0;
        multFactor = 1;
    }
    
    void append(int val) {
        // Store the value after removing current global operations
        // This ensures when we later apply global ops, we get the correct value
        long long normalized = ((val - addOffset + MOD) % MOD) * modInverse(multFactor) % MOD;
        sequence.push_back(normalized);
    }
    
    void addAll(int inc) {
        addOffset = (addOffset + inc) % MOD;
    }
    
    void multAll(int m) {
        // When multiplying, both the offset and factor are affected
        addOffset = (addOffset * m) % MOD;
        multFactor = (multFactor * m) % MOD;
    }
    
    int getIndex(int idx) {
        if (idx >= sequence.size()) {
            return -1;
        }
        
        // Apply current operations to get actual value
        long long result = (sequence[idx] * multFactor + addOffset) % MOD;
        return result;
    }
    
private:
    // Calculate modular multiplicative inverse using Fermat's Little Theorem
    long long modInverse(long long a) {
        return modPow(a, MOD - 2);
    }
    
    long long modPow(long long base, long long exponent) {
        long long result = 1;
        base %= MOD;
        
        while (exponent > 0) {
            if (exponent & 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exponent >>= 1;
        }
        
        return result;
    }
};

/**
 * Your Fancy object will be instantiated and called as such:
 * Fancy* obj = new Fancy();
 * obj->append(val);
 * obj->addAll(inc);
 * obj->multAll(m);
 * int param_4 = obj->getIndex(idx);
 */
