class Solution {
public:
    int preimageSizeFZF(int k) {
        long long left = 0, right = 5LL * (k + 1);
        
        while (left <= right) {
            long long mid = left + (right - left) / 2;
            long long zeros = countTrailingZeros(mid);
            
            if (zeros == k) {
                return 5;
            } else if (zeros < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return 0;
    }
    
private:
    long long countTrailingZeros(long long x) {
        long long count = 0;
        while (x > 0) {
            x /= 5;
            count += x;
        }
        return count;
    }
};
