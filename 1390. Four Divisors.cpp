class Solution {
public:
    int sumFourDivisors(vector<int>& nums) {
        int total = 0;
        
        for (int num : nums) {
            int divisorCount = 0;
            int divisorSum = 0;
            
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    if (i * i == num) {
                        divisorCount += 1;
                        divisorSum += i;
                    } else {
                        divisorCount += 2;
                        divisorSum += i + (num / i);
                    }
                }
                if (divisorCount > 4) break;
            }
            
            if (divisorCount == 4) {
                total += divisorSum;
            }
        }
        
        return total;
    }
};
