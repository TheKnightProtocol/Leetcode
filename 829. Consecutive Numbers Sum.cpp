class Solution {
public:
    int consecutiveNumbersSum(int n) {
        int count = 0;
        int limit = sqrt(2 * n);
        
        for (int k = 1; k <= limit; k++) {
            if ((2 * n) % k == 0) {
                int temp = (2 * n) / k - k + 1;
                if (temp > 0 && temp % 2 == 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
};
