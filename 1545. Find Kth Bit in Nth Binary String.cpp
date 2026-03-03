class Solution {
public:
    char findKthBit(int n, int k) {
        if (n == 1) return '0';
        
        int mid = pow(2, n - 1);
        
        if (k == mid) {
            return '1';
        } else if (k < mid) {
            return findKthBit(n - 1, k);
        } else {
            int mirroredK = 2 * mid - k;
            char bit = findKthBit(n - 1, mirroredK);
            return bit == '0' ? '1' : '0';
        }
    }
};
